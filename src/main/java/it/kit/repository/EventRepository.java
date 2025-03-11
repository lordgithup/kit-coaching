package it.kit.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import it.kit.entity.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Integer>{

	

    Page<EventEntity> findByName(String name, Pageable pageable);
    
}
