package com.aICE.API.controller;

import com.aICE.API.entity.Bot;
import com.aICE.API.service.BotService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bots")
@CrossOrigin("*")
public class BotController {

    private final BotService botService;

    public BotController(
            BotService botService
    ) {
        this.botService =
                botService;
    }

    @PostMapping
    public Bot createBot(

            @RequestBody Bot bot
    ) {

        return botService
                .createBot(bot);
    }

    @GetMapping
    public List<Bot> getAllBots() {

        return botService
                .getAllBots();
    }

    @GetMapping("/{id}")
    public Bot getBotById(

            @PathVariable Long id
    ) {

        return botService
                .getBotById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBot(

            @PathVariable Long id
    ) {

        botService.deleteBot(id);

        return "Bot deleted successfully";
    }
}