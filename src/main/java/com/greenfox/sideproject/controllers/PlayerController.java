package com.greenfox.sideproject.controllers;

import com.greenfox.sideproject.models.dtos.UserResponseDTO;
import com.greenfox.sideproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("apiKey")
public class PlayerController {

    UserService userService;

    @Autowired
    public PlayerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getMain(Model model) {
        if (model.getAttribute("apiKey") == null) {
            return "redirect:/login";
        }
        return "main";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/create-account")
    public String getRegister() { return "create-account"; }

    @PostMapping("/login")
    public String login(Model model, @RequestParam String login, @RequestParam String password) {
        String apiKey = userService.login(login, password);
        if (apiKey == null) {
            model.addAttribute("loginFailed", true);
            return "login";
        }
        model.addAttribute("apiKey", apiKey);
        return "redirect:/";
    }

    @PostMapping("/create-account")
    public String createAccount(Model model, @RequestParam String login,
                                @RequestParam String password) {
        UserResponseDTO userResponseDTO = userService.createAccount(login, password);

        if(userResponseDTO != null) {
            model.addAttribute("registrationSuccess", true);
            return "login";
        }
        model.addAttribute("registrationFail", true);
        return "create-account";
    }

    @GetMapping("/logout")
    public String logout(Model model, SessionStatus status) {
        userService.logout(model.getAttribute("apiKey").toString());
        status.setComplete();
        model.addAttribute("logoutSuccess", true);
        return "login";
    }

}
