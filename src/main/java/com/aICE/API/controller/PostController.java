package com.aICE.API.controller;

import com.aICE.API.entity.Post;
import com.aICE.API.service.PostService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {

    private final PostService postService;

    public PostController(
            PostService postService
    ) {
        this.postService = postService;
    }

    @PostMapping
    public Post createPost(
            @RequestBody Post post
    ) {

        return postService
                .createPost(post);
    }

    @GetMapping
    public List<Post> getAllPosts() {

        return postService
                .getAllPosts();
    }

    @GetMapping("/trending")
    public List<Post> getTrendingPosts() {

        return postService
                .getTrendingPosts();
    }

    @PutMapping("/{id}/like")
    public Post likePost(
            @PathVariable Long id
    ) {

        return postService
                .likePost(id);
    }

    @PutMapping("/{id}/comment/{userId}/{botId}")
    public Post commentPost(

            @PathVariable Long id,

            @PathVariable Long userId,

            @PathVariable Long botId
    ) {

        return postService
                .commentPost(
                        id,
                        userId,
                        botId
                );
    }
}