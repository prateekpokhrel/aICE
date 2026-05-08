package com.aICE.API.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class ViralityRedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public ViralityRedisService(
            RedisTemplate<String, Object> redisTemplate
    ) {
        this.redisTemplate = redisTemplate;
    }

    public double incrementViralityScore(
            Long postId,
            int points
    ) {

        String key =
                "post:" + postId + ":virality_score";

        Double score =
                redisTemplate
                        .opsForValue()
                        .increment(key, (double) points);

        return score != null ? score : 0;
    }

    public Object getViralityScore(
            Long postId
    ) {

        String key =
                "post:" + postId + ":virality_score";

        return redisTemplate
                .opsForValue()
                .get(key);
    }

    public synchronized long incrementBotReplyCount(
            Long postId
    ) {

        String key =
                "post:" + postId + ":bot_count";

        Object currentValue =
                redisTemplate
                        .opsForValue()
                        .get(key);

        long currentCount = 0;

        if (currentValue != null) {

            currentCount =
                    Long.parseLong(
                            currentValue.toString()
                    );
        }

        if (currentCount >= 100) {

            return currentCount;
        }

        Long updatedCount =
                redisTemplate
                        .opsForValue()
                        .increment(key);

        return updatedCount != null
                ? updatedCount
                : currentCount;
    }

    public boolean exceedsBotLimit(
            Long postId
    ) {

        String key =
                "post:" + postId + ":bot_count";

        Object value =
                redisTemplate
                        .opsForValue()
                        .get(key);

        if (value == null) {
            return false;
        }

        long count =
                Long.parseLong(value.toString());

        return count > 100;
    }

    public void resetBotReplyCount(
            Long postId
    ) {

        String key =
                "post:" + postId + ":bot_count";

        redisTemplate.delete(key);
    }

    public void resetViralityScore(
            Long postId
    ) {

        String key =
                "post:" + postId + ":virality_score";

        redisTemplate.delete(key);
    }

    public boolean activateCooldown(
            Long userId,
            Long postId
    ) {

        String key =
                "cooldown:user:" +
                        userId +
                        ":post:" +
                        postId;

        Boolean success =
                redisTemplate
                        .opsForValue()
                        .setIfAbsent(
                                key,
                                "ACTIVE",
                                10,
                                TimeUnit.MINUTES
                        );

        return Boolean.TRUE.equals(success);
    }

    public boolean canSendNotification(
            Long userId
    ) {

        String key =
                "user:" +
                        userId +
                        ":notif_cooldown";

        Boolean exists =
                redisTemplate.hasKey(key);

        return !Boolean.TRUE.equals(exists);
    }

    public void activateNotificationCooldown(
            Long userId
    ) {

        String key =
                "user:" +
                        userId +
                        ":notif_cooldown";

        redisTemplate
                .opsForValue()
                .set(

                        key,

                        "ACTIVE",

                        15,

                        TimeUnit.MINUTES
                );
    }

    public void queuePendingNotification(

            Long userId,

            String message
    ) {

        String key =
                "user:" +
                        userId +
                        ":pending_notifs";

        redisTemplate
                .opsForList()
                .rightPush(
                        key,
                        message
                );
    }

    public List<Object> getPendingNotifications(
            Long userId
    ) {

        String key =
                "user:" +
                        userId +
                        ":pending_notifs";

        return redisTemplate
                .opsForList()
                .range(
                        key,
                        0,
                        -1
                );
    }

    public Set<String> getPendingNotificationKeys() {

        return redisTemplate.keys(
                "user:*:pending_notifs"
        );
    }

    public Long getPendingNotificationCount(
            String key
    ) {

        return redisTemplate
                .opsForList()
                .size(key);
    }

    public void clearPendingNotifications(
            String key
    ) {

        redisTemplate.delete(key);
    }

    public void clearPendingNotifications(
            Long userId
    ) {

        String key =
                "user:" +
                        userId +
                        ":pending_notifs";

        redisTemplate.delete(key);
    }
}