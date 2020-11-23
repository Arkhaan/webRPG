package com.greenfox.sideproject.services;

import com.greenfox.sideproject.models.Panel;
import com.greenfox.sideproject.models.Story;
import com.greenfox.sideproject.models.Tale;
import com.greenfox.sideproject.models.User;
import com.greenfox.sideproject.models.dtos.UserStoriesDTO;

import java.util.List;

public interface TaleService {

    public List<Tale> getAllTales();

    List<UserStoriesDTO> getUserStories(Long userId);

    Tale getTaleById(Long taleId);

    void savePanel(Panel panel);

    Panel findFirstPanel(Long taleId);

    List<Long> getAllPanelIds();

    String getPanelTextById(Long id);

    void updatePanel(Long panelId, String panelText);

    void createNewStory(User user, Long taleId, Long panelId, String name, String gender, Integer strength, Integer intelligence, Integer agility);

    Panel getPanel(Long panelId);

    Story getUserStory(Long taleId, Long userId);
}
