package com.aICE.API.service;

import org.springframework.stereotype.Service;

@Service
public class ViralityService {

    public double calculateScore(
            int likes,
            int comments
    ) {

        return (likes * 2.0) + (comments * 3.5);

    }
}