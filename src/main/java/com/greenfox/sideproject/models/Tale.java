package com.greenfox.sideproject.models;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Tale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long firstPanelId;

    public Tale() {
    }

    public Tale(String title) {
        this.title = title;
    }

    public void setFirstPanelId(Long firstPanelId) {
        this.firstPanelId = firstPanelId;
    }
}
