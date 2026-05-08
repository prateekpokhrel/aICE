package com.aICE.API.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private Integer depthLevel = 0;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @OneToMany(
            mappedBy = "parentComment",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Comment> replies =
            new ArrayList<>();

    public Comment() {
    }

    public Comment(
            String message
    ) {

        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(
            Long id
    ) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(
            String message
    ) {
        this.message = message;
    }

    public Integer getDepthLevel() {
        return depthLevel;
    }

    public void setDepthLevel(
            Integer depthLevel
    ) {
        this.depthLevel = depthLevel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(
            User user
    ) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(
            Post post
    ) {
        this.post = post;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(
            Comment parentComment
    ) {
        this.parentComment =
                parentComment;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void setReplies(
            List<Comment> replies
    ) {
        this.replies = replies;
    }
}