package com.aICE.API.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class NotificationSweeperService {

    private final ViralityRedisService
            viralityRedisService;

    public NotificationSweeperService(
            ViralityRedisService
                    viralityRedisService
    ) {
        this.viralityRedisService =
                viralityRedisService;
    }

    @Scheduled(fixedRate = 300000)
    public void sweepNotifications() {

        Set<String> keys =

                viralityRedisService
                        .getPendingNotificationKeys();

        if (
                keys == null ||
                        keys.isEmpty()
        ) {

            System.out.println(
                    "No pending notifications"
            );

            return;
        }

        for (String key : keys) {

            Long count =

                    viralityRedisService
                            .getPendingNotificationCount(
                                    key
                            );

            if (
                    count != null &&
                            count > 0
            ) {

                System.out.println(

                        "Summarized Push Notification: " +

                                "Bot X and " +

                                count +

                                " others interacted with your posts."
                );

                viralityRedisService
                        .clearPendingNotifications(
                                key
                        );
            }
        }
    }
}