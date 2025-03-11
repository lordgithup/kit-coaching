package it.kit.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.kit.binding.NotificationForm;
import it.kit.entity.NotificationEntity;

public interface NotificationService {

   
    String createNotification(NotificationForm notificationForm);
    
    NotificationEntity updateNotification(NotificationEntity notification, Integer notificationId);

    String deleteNotificationById(Integer notificationId);
 
    Page<NotificationEntity> getAllEvents(String name, Pageable pageable);
}
