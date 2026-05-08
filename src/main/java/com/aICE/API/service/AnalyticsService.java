package com.aICE.API.service;

import com.aICE.API.dto.DashboardStatsDTO;
import com.aICE.API.repository.CommentRepository;
import com.aICE.API.repository.PostRepository;
import com.aICE.API.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    public AnalyticsService(
            UserRepository userRepository,
            PostRepository postRepository,
            CommentRepository commentRepository
    ) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public DashboardStatsDTO getDashboardStats() {

        long totalUsers =
                userRepository.count();

        long totalPosts =
                postRepository.count();

        long totalComments =
                commentRepository.count();

        double topViralityScore = 0;

        if (!postRepository.findAll().isEmpty()) {

            topViralityScore =
                    postRepository
                            .findAllByOrderByViralityScoreDesc()
                            .get(0)
                            .getViralityScore();
        }

        return new DashboardStatsDTO(
                totalUsers,
                totalPosts,
                totalComments,
                topViralityScore
        );
    }
}