package com.aICE.API.controller;

import com.aICE.API.dto.MonitorStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monitor")
@CrossOrigin("*")
public class MonitorController {

    @GetMapping
    public MonitorStatus getSystemStatus() {

        return new MonitorStatus(

                "ONLINE",

                "CONNECTED",

                "RUNNING",

                "ACTIVE"
        );
    }
}