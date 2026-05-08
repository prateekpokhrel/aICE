package com.aICE.API.dto;

public class AuthRequestDTO {

    private String username;

    public AuthRequestDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(
            String username
    ) {
        this.username = username;
    }
}