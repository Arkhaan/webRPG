package com.greenfox.sideproject.controllers;

import com.greenfox.sideproject.models.Panel;
import com.greenfox.sideproject.models.Story;
import com.greenfox.sideproject.models.dtos.UserResponseDTO;
import com.greenfox.sideproject.models.dtos.UserStoriesDTO;
import com.greenfox.sideproject.services.TaleService;
import com.greenfox.sideproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@SessionAttributes("apiKey")
public class PlayerController {

    UserService userService;
    TaleService taleService;

    @Autowired
    public PlayerController(UserService userService, TaleService taleService) {
        this.userService = userService;
        this.taleService = taleService;
    }

    @GetMapping("/")
    public String getMain(Model model) {
        if (model.getAttribute("apiKey") == null) {
            return "redirect:/login";
        }

        Long userId = userService.getUserId(model.getAttribute("apiKey").toString());
        model.addAttribute("user_id", userId);
        model.addAttribute("userStories", taleService.getUserStories(userId));
        return "tale-menu";
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
        return "redirect:/login";
    }

    @GetMapping("/panel-administration")
    public String getPanelAdministration(Model model) {
        model.addAttribute("tales", taleService.getAllTales());
        model.addAttribute("panelIds", taleService.getAllPanelIds());
        return "panel-administration";
    }

    @PostMapping("/create-panel")
    public String createPanel(Model model,
                              @RequestParam String panelText,
                              @RequestParam Long taleId) {
        taleService.savePanel(new Panel(panelText, taleService.getTaleById(taleId)));
        return "redirect:/panel-administration";
    }

    @PostMapping("/update-panel")
    public String updatePanel(@RequestParam Long panelId,
                              @RequestParam String panelText) {
        taleService.updatePanel(panelId, panelText);
        return "redirect:/panel-administration";
    }

    @GetMapping("/start-adventure")
    public String getStartAdventure(@RequestParam("id") Long taleId, Model model) {
        Panel panel = taleService.findFirstPanel(taleId);
        model.addAttribute("panel", panel);
        model.addAttribute("taleId", taleId);
        return "tale-start";
    }

    @PostMapping("/start-adventure")
    public String startAdventure(Model model,
                                 RedirectAttributes attributes,
                                 @RequestParam("id") Long taleId,
                                 @RequestParam("panelStart") Long panelId,
                                 @RequestParam String name,
                                 @RequestParam String gender,
                                 @RequestParam Integer strength,
                                 @RequestParam Integer intelligence,
                                 @RequestParam Integer agility) {
        taleService.createNewStory(userService.getUser(model.getAttribute("apiKey").toString()), taleId, panelId, name, gender, strength, intelligence, agility);
        attributes.addFlashAttribute("panel", taleService.getPanel(panelId));
        return "redirect:/adventure?taleId="+taleId;
    }

    @GetMapping("adventure")
    public String loadAdventure(Model model,
                                @RequestParam(required = false) Long panelId,
                                @RequestParam(required = false) Long taleId) {
        if(panelId == null) {
            Story story = taleService.getUserStory(taleId, userService.getUserId(model.getAttribute("apiKey").toString()));
            model.addAttribute("panel", taleService.getPanel(story.getPanel().getId()));
        } else {
            model.addAttribute("panel", taleService.getPanel(panelId));
        }
        return "adventure";
    }

    @ResponseBody
    @GetMapping("/get-panel-text")
    public String getPanelText(@RequestParam Long id) {
        String panelText = taleService.getPanelTextById(id).replace("\r\n", "&#13;&#10;");
        return panelText;
    }

}
