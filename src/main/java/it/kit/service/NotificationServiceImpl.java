package it.kit.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.kit.binding.NotificationForm;
import it.kit.entity.NotificationEntity;
import it.kit.exception.NotificationNotFoundException;
import it.kit.repository.NotificationRepo;


@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepo notificationRepo;

    @Override
    public String createNotification(NotificationForm notificationForm) {
        NotificationEntity notificationEntity = new NotificationEntity();
        BeanUtils.copyProperties(notificationForm, notificationEntity);
        NotificationEntity savedNotification = notificationRepo.save(notificationEntity);
        return savedNotification != null ? "Notification is registered" : "Problem in notification registration";
    }

    @Override
    public NotificationEntity updateNotification(NotificationEntity notification, Integer notificationid) {
        return notificationRepo.findById(notificationid).map(existing -> {
            existing.setName(notification.getName());
            return notificationRepo.save(existing);
        }).orElseThrow(() -> new NotificationNotFoundException("Notification not found with ID: " + notificationid));
    }

    @Override
    public String deleteNotificationById(Integer notificationid) {
        Optional<NotificationEntity> opt = notificationRepo.findById(notificationid);
        if (opt.isPresent()) {
            notificationRepo.deleteById(notificationid);
            return "Notification deleted successfully.";
        }
        throw new NotificationNotFoundException("Notification not found with ID: " + notificationid);
    }

    @Override
    public Page<NotificationEntity> getAllEvents(String name, Pageable pageable) {
        return notificationRepo.findAll(pageable);
    }
}
