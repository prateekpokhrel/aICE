package com.aICE.API.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bots")
public class Bot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 1000)
    private String personaDescription;

    public Bot() {
    }

    public Bot(
            String name,
            String personaDescription
    ) {
        this.name = name;
        this.personaDescription =
                personaDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(
            String name
    ) {
        this.name = name;
    }

    public String getPersonaDescription() {
        return personaDescription;
    }

    public void setPersonaDescription(
            String personaDescription
    ) {
        this.personaDescription =
                personaDescription;
    }
}