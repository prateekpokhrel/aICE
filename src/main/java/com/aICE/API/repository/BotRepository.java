package com.aICE.API.repository;

import com.aICE.API.entity.Bot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BotRepository
        extends JpaRepository<Bot, Long> {
}