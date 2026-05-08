package com.aICE.API.controller;

import com.aICE.API.entity.Comment;
import com.aICE.API.service.CommentService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin("*")
public class CommentController {

    private final CommentService commentService;

    public CommentController(
            CommentService commentService
    ) {

        this.commentService =
                commentService;
    }

    @PostMapping("/post/{postId}")
    public Comment createRootComment(

            @PathVariable Long postId,

            @RequestBody Comment comment
    ) {

        return commentService
                .createRootComment(
                        postId,
                        comment
                );
    }

    @PostMapping("/reply/{parentCommentId}")
    public Comment replyToComment(

            @PathVariable Long parentCommentId,

            @RequestBody Comment reply
    ) {

        return commentService
                .replyToComment(
                        parentCommentId,
                        reply
                );
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getRootComments(

            @PathVariable Long postId
    ) {

        return commentService
                .getRootComments(postId);
    }

    @GetMapping("/reply/{parentCommentId}")
    public List<Comment> getReplies(

            @PathVariable Long parentCommentId
    ) {

        return commentService
                .getReplies(parentCommentId);
    }
}