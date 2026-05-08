package com.aICE.API.scheduler;

import com.aICE.API.service.ViralityRedisService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class NotificationScheduler {

    private final ViralityRedisService viralityRedisService;

    public NotificationScheduler(
            ViralityRedisService viralityRedisService
    ) {
        this.viralityRedisService =
                viralityRedisService;
    }

    @Scheduled(fixedRate = 300000)
    public void processPendingNotifications() {

        Set<String> keys =
                viralityRedisService
                        .getPendingNotificationKeys();

        if (keys == null || keys.isEmpty()) {

            System.out.println(
                    "No pending notifications"
            );

            return;
        }

        for (String key : keys) {

            Long count =
                    viralityRedisService
                            .getPendingNotificationCount(key);

            System.out.println(
                    "Summarized Push Notification: " +
                            count +
                            " pending interactions"
            );

            viralityRedisService
                    .clearPendingNotifications(key);
        }
    }
}