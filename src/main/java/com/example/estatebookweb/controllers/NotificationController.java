package com.example.estatebookweb.controllers;

import com.example.estatebookweb.models.NotificationModel;
import com.example.estatebookweb.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @PostMapping("/createNotification")
    public ResponseEntity<String> createNotification(@RequestBody NotificationModel notificationModel) {
        notificationRepository.save(notificationModel);
        return ResponseEntity.status(HttpStatus.OK).body("Объявление было успешно добавлено");
    }
}