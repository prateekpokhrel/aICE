package com.aICE.API.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketNotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketNotificationService(
            SimpMessagingTemplate messagingTemplate
    ) {
        this.messagingTemplate =
                messagingTemplate;
    }

    public void sendLiveNotification(
            String message
    ) {

        messagingTemplate.convertAndSend(
                "/topic/notifications",
                message
        );
    }

    public void sendLiveAnalyticsUpdate(
            Object analytics
    ) {

        messagingTemplate.convertAndSend(
                "/topic/analytics",
                analytics
        );
    }
}