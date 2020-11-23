package com.greenfox.sideproject.services;

import com.greenfox.sideproject.models.Panel;

public interface PanelService {

    void savePanel(Panel panel);

    Panel findById(Long panelId);
}
