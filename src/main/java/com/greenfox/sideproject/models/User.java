package com.greenfox.sideproject.models;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    @Column(name = "api_key", unique = true)
    private String apiKey;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
