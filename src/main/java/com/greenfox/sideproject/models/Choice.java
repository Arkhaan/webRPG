package com.greenfox.sideproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Choice {

    private String text;
    private Long destinationPanelId;
    private String test;
    private Integer testThreshold;

    public Choice() {
    }

    public Choice(String text, Long destinationPanelId) {
        this.text = text;
        this.destinationPanelId = destinationPanelId;
    }

    public Choice(String text, Long destinationPanelId, String test, Integer testThreshold) {
        this.text = text;
        this.destinationPanelId = destinationPanelId;
        this.test = test;
        this.testThreshold = testThreshold;
    }

}
