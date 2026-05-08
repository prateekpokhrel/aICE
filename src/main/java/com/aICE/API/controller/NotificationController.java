package com.aICE.API.controller;

import com.aICE.API.entity.Notification;
import com.aICE.API.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@CrossOrigin("*")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(
            NotificationService notificationService
    ) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public Notification createNotification(
            @RequestBody Notification notification
    ) {
        return notificationService.createNotification(
                notification
        );
    }

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }
}