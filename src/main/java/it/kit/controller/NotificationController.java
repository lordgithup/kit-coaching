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
import org.springframework.web.bind.annotation.*;

import it.kit.binding.NotificationForm;
import it.kit.entity.NotificationEntity;
import it.kit.service.NotificationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/notification-api")
@CrossOrigin("http://localhost:3000")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/save")
    public ResponseEntity<String> saveNotification(@RequestBody @Valid NotificationForm notification) {
        try {
            String resultMsg = notificationService.createNotification(notification);
            return new ResponseEntity<>(resultMsg, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<NotificationEntity> updateNotification(@RequestBody NotificationEntity notification, @PathVariable("id") Integer notificationid) {
        try {
            NotificationEntity updatedNotification = notificationService.updateNotification(notification, notificationid);
            return ResponseEntity.ok(updatedNotification);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNotificationById(@PathVariable("id") Integer id) {
        try {
            String resultMsg = notificationService.deleteNotificationById(id);
            return ResponseEntity.ok(resultMsg);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/notifications")
    public ResponseEntity<Map<String, Object>> getNotifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "notificationid") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) 
                    ? Sort.by(sortBy).ascending() 
                    : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<NotificationEntity> notificationPage = notificationService.getAllEvents("", pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("data", notificationPage.getContent());
        response.put("currentPage", notificationPage.getNumber());
        response.put("totalItems", notificationPage.getTotalElements());
        response.put("totalPages", notificationPage.getTotalPages());

        return ResponseEntity.ok(response);
    }
}
