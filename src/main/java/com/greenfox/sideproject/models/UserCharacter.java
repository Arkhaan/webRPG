package com.greenfox.sideproject.models;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Embeddable
public class UserCharacter {

    private String name;
    private String gender;
    private Integer health;
    private Integer agility;
    private Integer strength;
    private Integer intelligence;

    public UserCharacter() {
    }

    public UserCharacter(String name, String gender, Integer strength, Integer intelligence, Integer agility) {
        this.name = name;
        this.gender = gender;
        this.agility = agility;
        this.strength = strength;
        this.intelligence = intelligence;
        this.health = 50;
    }
}
