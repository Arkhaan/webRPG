package com.greenfox.sideproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlayerController {

    @GetMapping("/")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/main")
    public String getMain() {
        return "main";
    }

}
