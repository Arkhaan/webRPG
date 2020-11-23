package com.greenfox.sideproject.services;

import com.greenfox.sideproject.models.Panel;
import com.greenfox.sideproject.repositories.PanelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PanelServiceImpl implements PanelService {

    PanelRepository panelRepository;

    @Autowired
    public PanelServiceImpl(PanelRepository panelRepository) {
        this.panelRepository = panelRepository;
    }

    @Override
    public void savePanel(Panel panel) {
        panelRepository.save(panel);
    }

    @Override
    public Panel findById(Long panelId) {
        return panelRepository.findById(panelId).get();
    }
}
