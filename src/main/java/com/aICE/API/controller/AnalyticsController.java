package com.aICE.API.controller;

import com.aICE.API.dto.DashboardStatsDTO;
import com.aICE.API.service.AnalyticsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics")
@CrossOrigin("*")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(
            AnalyticsService analyticsService
    ) {
        this.analyticsService =
                analyticsService;
    }

    @GetMapping("/dashboard")
    public DashboardStatsDTO getDashboardStats() {

        return analyticsService
                .getDashboardStats();
    }
}