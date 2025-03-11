package it.kit.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.kit.binding.EnquiryForm;
import it.kit.entity.EnquiryEntity;


public interface EnquiryService {

	
	public String createEnquiry(EnquiryForm Enquiry);
	
	public EnquiryEntity updateEnquiry(EnquiryEntity Enquiry, Integer  enquaryid);

	public String deleteEnquiryById(Integer enquaryid);
	
	public Page<EnquiryEntity> getAllEnquirys(String name,  Pageable pageable);
	


}
