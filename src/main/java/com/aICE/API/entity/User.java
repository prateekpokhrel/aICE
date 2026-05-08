package com.aICE.API.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private boolean isPremium;

    @JsonIgnore
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Post> posts =
            new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Comment> comments =
            new ArrayList<>();

    public User() {
    }

    public User(
            String username,
            boolean isPremium
    ) {

        this.username = username;
        this.isPremium = isPremium;
    }

    public Long getId() {
        return id;
    }

    public void setId(
            Long id
    ) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(
            String username
    ) {
        this.username = username;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(
            boolean premium
    ) {
        isPremium = premium;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(
            List<Post> posts
    ) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(
            List<Comment> comments
    ) {
        this.comments = comments;
    }
}