package com.aICE.API.controller;

import com.aICE.API.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/stress")
@CrossOrigin("*")
public class StressTestController {

    private final PostService postService;

    public StressTestController(
            PostService postService
    ) {
        this.postService = postService;
    }

    @PostMapping("/spam/{postId}")
    public String spamTest(

            @PathVariable Long postId
    ) {

        ExecutorService executorService =

                Executors.newFixedThreadPool(
                        200
                );

        for (int i = 0; i < 200; i++) {

            final long userId = 999L;

            executorService.submit(() -> {

                try {

                    postService
                            .stressCommentPost(
                                    postId
                            );

                } catch (Exception e) {

                    System.out.println(
                            e.getMessage()
                    );
                }
            });
        }

        executorService.shutdown();

        return
                "200 concurrent requests fired";
    }
}