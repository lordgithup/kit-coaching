package it.kit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.kit.entity.BlogEntity;

public interface BlogRepository  extends JpaRepository<BlogEntity, Integer>{
 	
}
