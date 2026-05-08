package com.aICE.API.controller;

import com.aICE.API.dto.AuthRequestDTO;
import com.aICE.API.dto.AuthResponseDTO;
import com.aICE.API.security.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(
            JwtService jwtService
    ) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public AuthResponseDTO login(

            @RequestBody
            AuthRequestDTO request
    ) {

        String token =
                jwtService.generateToken(
                        request.getUsername()
                );

        return new AuthResponseDTO(
                token
        );
    }
}