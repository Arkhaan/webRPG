package com.greenfox.sideproject.models.dtos;

import lombok.Getter;

@Getter
public class UserStoriesDTO {

    private String taleName;
    private Long taleId;
    private Boolean inProgress;

    public UserStoriesDTO() {
    }

    public UserStoriesDTO(String taleName, Long taleId, Boolean inProgress) {
        this.taleName = taleName;
        this.taleId = taleId;
        this.inProgress = inProgress;
    }
}
