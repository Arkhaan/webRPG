package com.greenfox.sideproject.services;

import com.greenfox.sideproject.models.dtos.UserResponseDTO;

public interface UserService {

    public String login(String login, String password);

    UserResponseDTO createAccount(String login, String password);

    void logout(String apikey);
}
