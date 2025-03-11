package it.kit.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import it.kit.entity.EnquiryEntity;

import java.util.List;


public interface EnquaryRepository extends JpaRepository<EnquiryEntity, Integer>{

	
    
    Page<EnquiryEntity> findByName(String name, Pageable pageable);
    
}
