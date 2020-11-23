package com.greenfox.sideproject.services;

import com.greenfox.sideproject.models.Story;
import com.greenfox.sideproject.models.Tale;
import com.greenfox.sideproject.models.User;
import com.greenfox.sideproject.models.dtos.UserResponseDTO;
import com.greenfox.sideproject.models.dtos.UserStoriesDTO;
import com.greenfox.sideproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String login(String login, String password) {
        // 1 - check if user in database
        Optional<User> optionalUser = userRepository.findByLoginAndPassword(login, password);
        if (optionalUser.isPresent()) {
            // 2 - Create generate and save api key
            String apiKey = generateApiKey();
            User user = optionalUser.get();
            user.setApiKey(apiKey);
            userRepository.save(user);
            return apiKey;
        }
        return null;

    }

    @Override
    public UserResponseDTO createAccount(String login, String password) {
        // 1 - check if user already exists
        Optional<User> user = userRepository.findByLoginAndPassword(login, password);
        if (user.isPresent()) {
            return null;
        }
        // 2 - Create new user
        userRepository.save(new User(login, password));
        return new UserResponseDTO(login);

    }

    @Override
    public void logout(String apiKey) {
        // TODO : make boolean and say if logout was successful or not
        Optional<User> optionalUser = userRepository.findByApiKey(apiKey);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setApiKey(null);
            userRepository.save(user);
        }
    }

    @Override
    public Long getUserId(String apiKey) {
        Optional<User> optionalUser = userRepository.findByApiKey(apiKey);
        return optionalUser.map(User::getId).orElse(null);
    }

    @Override
    public User getUser(String apiKey) {
        return userRepository.findByApiKey(apiKey).get();
    }

    public String generateApiKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] values = new byte[24];
        secureRandom.nextBytes(values);
        return values.toString();
    }
}
