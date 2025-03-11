package it.kit.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import it.kit.entity.NotificationEntity;

public interface NotificationRepo extends JpaRepository<NotificationEntity, Integer>{

}
