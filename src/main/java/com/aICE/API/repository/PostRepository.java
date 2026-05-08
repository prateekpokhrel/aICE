package com.aICE.API.repository;

import com.aICE.API.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository
        extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByViralityScoreDesc();
}