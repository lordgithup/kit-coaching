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

import it.kit.binding.EventForm;
import it.kit.entity.EnquiryEntity;
import it.kit.entity.EventEntity;
import it.kit.service.EventService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Event-api")
@CrossOrigin("http://localhost:3000")
public class EventController {
	
	
	@Autowired
	private EventService eventService;
	
	@PostMapping("/saveEvent")
	public ResponseEntity<String> saveEvent(@RequestBody @Valid EventForm event){		
		try {
			//user service
			 String resultMsg= eventService.createEvent(event);
			 return new ResponseEntity<String>(resultMsg,HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	 @PutMapping("/update/{id}")
	    public ResponseEntity<EventEntity> updateEvent(@RequestBody EventEntity event, @PathVariable("id") Integer eventid) {
	        try {
	            EventEntity updatedEvent = eventService.updateEvent(event, eventid);
	            return ResponseEntity.ok(updatedEvent);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	@DeleteMapping("/deleteEvent/{id}")
	public ResponseEntity<String> deleteEventById(@PathVariable Integer id){		
		try {
			//user service
			 String resultMsg= eventService.deleteEventById(id);
			 return new ResponseEntity<String>(resultMsg,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@GetMapping("/Events")
    public ResponseEntity<Map<String, Object>> getEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "eventid") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "") String search) {
            
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) 
                    ? Sort.by(sortBy).ascending() 
                    : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<EventEntity> EventPage = eventService.getAllEvents(search, pageable);

        // Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("data", EventPage.getContent());
        response.put("currentPage", EventPage.getNumber());
        response.put("totalItems",EventPage.getTotalElements());
        response.put("totalPages", EventPage.getTotalPages());
        return ResponseEntity.ok(response);
    }
	
}





