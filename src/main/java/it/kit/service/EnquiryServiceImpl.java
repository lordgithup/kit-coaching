package it.kit.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.kit.binding.EnquiryForm;
import it.kit.entity.EnquiryEntity;
import it.kit.exception.EnquiryNotFoundException;
import it.kit.repository.EnquaryRepository;


@Service
public class EnquiryServiceImpl implements EnquiryService{

	@Autowired
	private EnquaryRepository enquaryRepo;
	
	@Override
	public String createEnquiry(EnquiryForm Enquiry) {
		// TODO Auto-generated method stub
		EnquiryEntity master = new EnquiryEntity();
		BeanUtils.copyProperties(Enquiry, master);
		
		EnquiryEntity savedEnquary= enquaryRepo.save(master);				
			return savedEnquary != null? "enquary is registered" : "Problem is Enquary  registration";
	}


	
	@Override
	public EnquiryEntity updateEnquiry(EnquiryEntity enquiry, Integer enquiryId) {
	    
	    return enquaryRepo.findById(enquiryId).map(existingEnquiry -> {
	        existingEnquiry.setName(enquiry.getName());
	        existingEnquiry.setEmail(enquiry.getEmail());
	        existingEnquiry.setPhone(enquiry.getPhone());
	        existingEnquiry.setMessage(enquiry.getMessage());
	        existingEnquiry.setSubject(enquiry.getSubject());
	        return enquaryRepo.save(existingEnquiry);
	    }).orElseThrow(() -> new EnquiryNotFoundException("Sorry, this enquiry could not be found"));
	}

	

	@Override
	public String deleteEnquiryById(Integer enquiryId) {
	    Optional<EnquiryEntity> opt = enquaryRepo.findById(enquiryId);
	    
	    if (opt.isPresent()) {
	    	enquaryRepo.deleteById(enquiryId);
	        return "Enquiry with ID " + enquiryId + " has been deleted successfully.";
	    }

	    throw new EnquiryNotFoundException("Enquiry not found with ID: " + enquiryId);
	}

	
	


	@Override
	public Page<EnquiryEntity> getAllEnquirys(String name, Pageable pageable) {
		// TODO Auto-generated method stub

		 if (name != null && !name.isEmpty()) {
	            return enquaryRepo.findByName(name, pageable);
	        }
	        
	        // If no filters are provided, return all records
	        else {
	            return enquaryRepo.findAll(pageable);
	        }
	}
	
	
	


}
