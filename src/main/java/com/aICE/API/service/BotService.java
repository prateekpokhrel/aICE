package com.aICE.API.service;

import com.aICE.API.entity.Bot;
import com.aICE.API.repository.BotRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BotService {

    private final BotRepository botRepository;

    public BotService(
            BotRepository botRepository
    ) {
        this.botRepository =
                botRepository;
    }

    public Bot createBot(
            Bot bot
    ) {

        return botRepository.save(bot);
    }

    public List<Bot> getAllBots() {

        return botRepository.findAll();
    }

    public Bot getBotById(
            Long id
    ) {

        return botRepository
                .findById(id)
                .orElseThrow(() ->

                        new RuntimeException(
                                "Bot not found"
                        ));
    }

    public void deleteBot(
            Long id
    ) {

        botRepository.deleteById(id);
    }
}