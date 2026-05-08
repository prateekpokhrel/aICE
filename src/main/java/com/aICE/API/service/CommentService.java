package com.aICE.API.service;

import com.aICE.API.entity.Comment;
import com.aICE.API.entity.Post;

import com.aICE.API.repository.CommentRepository;
import com.aICE.API.repository.PostRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    public CommentService(

            CommentRepository commentRepository,

            PostRepository postRepository
    ) {

        this.commentRepository =
                commentRepository;

        this.postRepository =
                postRepository;
    }

    public Comment createRootComment(

            Long postId,

            Comment comment
    ) {

        Post post =

                postRepository
                        .findById(postId)
                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Post not found"
                                ));

        comment.setPost(post);

        comment.setDepthLevel(0);

        return commentRepository.save(comment);
    }

    public Comment replyToComment(

            Long parentCommentId,

            Comment reply
    ) {

        Comment parent =

                commentRepository
                        .findById(parentCommentId)
                        .orElseThrow(() ->

                                new RuntimeException(
                                        "Parent comment not found"
                                ));
        int currentDepth =
                parent.getDepthLevel() != null
                        ? parent.getDepthLevel()
                        : 0;

        int nextDepth =
                currentDepth + 1;

        if (nextDepth > 20) {

            throw new RuntimeException(
                    "Maximum comment depth exceeded"
            );
        }

        reply.setParentComment(parent);

        reply.setPost(parent.getPost());

        reply.setDepthLevel(nextDepth);

        return commentRepository.save(reply);
    }

    public List<Comment> getRootComments(

            Long postId
    ) {

        return commentRepository
                .findByPostIdAndParentCommentIsNull(
                        postId
                );
    }

    public List<Comment> getReplies(

            Long parentCommentId
    ) {

        return commentRepository
                .findByParentCommentId(
                        parentCommentId
                );
    }
}