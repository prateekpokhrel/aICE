package com.aICE.API.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 5000)
    private String content;

    private int likes = 0;

    private int comments = 0;

    private double viralityScore = 0;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Comment> commentsList =
            new ArrayList<>();

    public Post() {
    }

    public Post(
            String title,
            String content
    ) {

        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(
            Long id
    ) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(
            String title
    ) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(
            String content
    ) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(
            int likes
    ) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(
            int comments
    ) {
        this.comments = comments;
    }

    public double getViralityScore() {
        return viralityScore;
    }

    public void setViralityScore(
            double viralityScore
    ) {
        this.viralityScore =
                viralityScore;
    }

    public User getUser() {
        return user;
    }

    public void setUser(
            User user
    ) {
        this.user = user;
    }

    public List<Comment> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(
            List<Comment> commentsList
    ) {
        this.commentsList =
                commentsList;
    }
}