package com.greenfox.sideproject.services;

import com.greenfox.sideproject.models.*;
import com.greenfox.sideproject.models.dtos.UserStoriesDTO;
import com.greenfox.sideproject.repositories.PanelRepository;
import com.greenfox.sideproject.repositories.StoryRepository;
import com.greenfox.sideproject.repositories.TaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaleServiceImpl implements TaleService {

    TaleRepository taleRepository;
    PanelRepository panelRepository;
    StoryRepository storyRepository;

    @Autowired
    public TaleServiceImpl(TaleRepository taleRepository, PanelRepository panelRepository, StoryRepository storyRepository) {
        this.taleRepository = taleRepository;
        this.panelRepository = panelRepository;
        this.storyRepository = storyRepository;
    }

    @Override
    public List<Tale> getAllTales() {
        return taleRepository.findAll();
    }

    @Override
    public List<UserStoriesDTO> getUserStories(Long userId) {
        List<UserStoriesDTO> userStoriesDTOList = new ArrayList<>();
        for (Tale tale: taleRepository.findAll()) {
            Long storyId = storyRepository.findIdByTaleIdAndUserId(tale.getId(), userId);
            if (storyId == null) {
                userStoriesDTOList.add(new UserStoriesDTO(tale.getTitle(), tale.getId(), false));
            } else {
                userStoriesDTOList.add(new UserStoriesDTO(tale.getTitle(), tale.getId(), true));
            }
        }
        return userStoriesDTOList;
    }

    @Override
    public Tale getTaleById(Long taleId) {
        return taleRepository.findById(taleId).get();
    }

    @Override
    public void savePanel(Panel panel) {
        panelRepository.save(panel);
    }

    @Override
    public Panel findFirstPanel(Long taleId) {
        Tale tale = taleRepository.findById(taleId).get();
        return panelRepository.findById(tale.getFirstPanelId()).get();
    }

    @Override
    public List<Long> getAllPanelIds() {
        return panelRepository.findAllIds();
    }

    @Override
    public String getPanelTextById(Long id) {
        return panelRepository.getOne(id).getMainText();
    }

    @Override
    public void updatePanel(Long panelId, String panelText) {
        Panel panel = panelRepository.findById(panelId).get();
        panel.setMainText(panelText);
        panelRepository.save(panel);
    }

    @Override
    public void createNewStory(User user, Long taleId, Long panelId, String name, String gender, Integer strength, Integer intelligence, Integer agility) {
        Long storyId = storyRepository.findIdByTaleIdAndUserId(user.getId(), taleId);
        if (storyId != null) {
            Story story = storyRepository.getOne(storyId);
            story.setUserCharacter(new UserCharacter(name, gender, strength, intelligence, agility));
            story.setPanel(panelRepository.getOne(panelId));
            storyRepository.save(story);
        } else {
            storyRepository.save(new Story(user, new UserCharacter(name, gender, strength, intelligence, agility),
                    getTaleById(taleId),
                    getPanel(panelId)
            ));
        }

    }

    @Override
    public Panel getPanel(Long panelId) {
        return panelRepository.getOne(panelId);
    }

    @Override
    public Story getUserStory(Long taleId, Long userId) {
        return storyRepository.findByTaleIdAndUserId(taleId, userId);
    }
}
