package com.greenfox.sideproject.services;

import com.greenfox.sideproject.models.User;
import com.greenfox.sideproject.models.dtos.UserResponseDTO;
import com.greenfox.sideproject.models.dtos.UserStoriesDTO;

public interface UserService {

    String login(String login, String password);

    UserResponseDTO createAccount(String login, String password);

    void logout(String apiKey);

    Long getUserId(String apiKey);

    User getUser(String apiKey);
}
