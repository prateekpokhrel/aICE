package com.aICE.API.service;

import com.aICE.API.entity.Notification;
import com.aICE.API.entity.Post;
import com.aICE.API.repository.NotificationRepository;
import com.aICE.API.repository.PostRepository;
import com.aICE.API.service.BotService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final ViralityService viralityService;

    private final ViralityRedisService viralityRedisService;

    private final NotificationRepository notificationRepository;

    private final WebSocketNotificationService
            webSocketNotificationService;

    private final BotService botService;

    public PostService(
            PostRepository postRepository,
            ViralityService viralityService,
            ViralityRedisService viralityRedisService,
            NotificationRepository notificationRepository,
            WebSocketNotificationService
                    webSocketNotificationService,
            BotService botService
    ) {
        this.postRepository = postRepository;
        this.viralityService = viralityService;
        this.viralityRedisService =
                viralityRedisService;
        this.notificationRepository =
                notificationRepository;
        this.webSocketNotificationService =
                webSocketNotificationService;
        this.botService = botService;
    }

    public Post createPost(Post post) {

        double score =
                viralityService.calculateScore(
                        post.getLikes(),
                        post.getComments()
                );

        post.setViralityScore(score);

        Post savedPost =
                postRepository.save(post);

        viralityRedisService
                .incrementViralityScore(
                        savedPost.getId(),
                        0
                );

        webSocketNotificationService
                .sendLiveAnalyticsUpdate(
                        savedPost
                );

        return savedPost;
    }

    public List<Post> getAllPosts() {

        return postRepository.findAll();
    }

    public List<Post> getTrendingPosts() {

        return postRepository
                .findAllByOrderByViralityScoreDesc();
    }

    public Post likePost(Long id) {

        Post post = postRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Post not found"
                        ));

        post.setLikes(
                post.getLikes() + 1
        );

        double redisScore =
                viralityRedisService
                        .incrementViralityScore(
                                id,
                                20
                        );

        post.setViralityScore(redisScore);

        if (redisScore >= 100) {

            Long userId =
                    post.getUser().getId();

            boolean canSendNow =
                    viralityRedisService
                            .canSendNotification(
                                    userId
                            );

            if (canSendNow) {

                viralityRedisService
                        .activateNotificationCooldown(
                                userId
                        );

                Notification notification =
                        new Notification(
                                "Post '" +
                                        post.getTitle() +
                                        "' is going viral!"
                        );

                notificationRepository
                        .save(notification);

                webSocketNotificationService
                        .sendLiveNotification(
                                notification.getMessage()
                        );

                System.out.println(
                        "Push Notification Sent to User"
                );

            } else {

                viralityRedisService
                        .queuePendingNotification(
                                userId,
                                "More activity on post: "
                                        + post.getTitle()
                        );
            }
        }

        Post updatedPost =
                postRepository.save(post);

        webSocketNotificationService
                .sendLiveAnalyticsUpdate(
                        updatedPost
                );

        return updatedPost;
    }

    public Post commentPost(
            Long id,
            Long userId,
            Long botId
    ) {

        Post post = postRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Post not found"
                        ));

        String botName =

                botService
                        .getBotById(botId)
                        .getName();

        boolean cooldownAllowed =
                viralityRedisService
                        .activateCooldown(
                                userId,
                                id
                        );

        if (!cooldownAllowed) {

            throw new RuntimeException(
                    "429 COMMENT COOLDOWN ACTIVE"
            );
        }

        long botCount =
                viralityRedisService
                        .incrementBotReplyCount(id);

        if (botCount > 100) {

            throw new RuntimeException(
                    "429 TOO MANY BOT REPLIES"
            );
        }

        post.setComments(
                post.getComments() + 1
        );

        double redisScore =
                viralityRedisService
                        .incrementViralityScore(
                                id,
                                50
                        );

        post.setViralityScore(redisScore);

        if (redisScore >= 100) {

            boolean canSendNow =
                    viralityRedisService
                            .canSendNotification(
                                    userId
                            );

            if (canSendNow) {

                viralityRedisService
                        .activateNotificationCooldown(
                                userId
                        );

                Notification notification =
                        new Notification(
                                botName +
                                        " interacted with post '" +
                                        post.getTitle() +
                                        "'"
                        );

                notificationRepository
                        .save(notification);

                webSocketNotificationService
                        .sendLiveNotification(
                                notification.getMessage()
                        );

                System.out.println(
                        "Push Notification Sent to User"
                );

            } else {

                viralityRedisService
                        .queuePendingNotification(
                                userId,
                                botName +
                                        " interacted again on post: "
                                        + post.getTitle()
                        );
            }
        }

        Post updatedPost =
                postRepository.save(post);

        webSocketNotificationService
                .sendLiveAnalyticsUpdate(
                        updatedPost
                );

        return updatedPost;
    }

    public void stressCommentPost(
            Long id
    ) {

        long botCount =

                viralityRedisService
                        .incrementBotReplyCount(id);

        if (botCount > 100) {

            throw new RuntimeException(
                    "429 TOO MANY BOT REPLIES"
            );
        }
    }
}