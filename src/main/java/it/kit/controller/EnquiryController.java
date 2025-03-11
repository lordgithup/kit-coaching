package it.kit.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.kit.binding.EnquiryForm;
import it.kit.entity.EnquiryEntity;
import it.kit.service.EnquiryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Enquary-api")
@CrossOrigin("http://localhost:3000")
public class EnquiryController {

	@Autowired
	private EnquiryService enquiryService;
	
	@PostMapping("/saveEnquary")
	public ResponseEntity<String> saveEnquary(@RequestBody @Valid EnquiryForm enquary){		
		try {
			//user service
			 String resultMsg= enquiryService.createEnquiry(enquary);
			 return new ResponseEntity<String>(resultMsg,HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update/{id}")
	  public EnquiryEntity updateEnquary(@RequestBody EnquiryEntity  Enquiry, @PathVariable Integer enquaryid) {
		  return enquiryService.updateEnquiry(null, enquaryid);
	  }

	
	
	@DeleteMapping("/deleteEnquary/{id}")
	public ResponseEntity<String> deleteEnquiryById(@PathVariable Integer id){		
		try {
			//user service
			 String resultMsg= enquiryService.deleteEnquiryById(id);
			 return new ResponseEntity<String>(resultMsg,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/Enquarys")
    public ResponseEntity<Map<String, Object>> getEnquarys(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "enquaryid") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "") String search) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) 
                    ? Sort.by(sortBy).ascending() 
                    : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<EnquiryEntity> EnquaryPage = enquiryService.getAllEnquirys(search, pageable);

        // Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("data", EnquaryPage.getContent());
        response.put("currentPage", EnquaryPage.getNumber());
        response.put("totalItems",EnquaryPage.getTotalElements());
        response.put("totalPages", EnquaryPage.getTotalPages());
        return ResponseEntity.ok(response);
    }
	
}





