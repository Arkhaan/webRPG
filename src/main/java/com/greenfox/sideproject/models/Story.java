package com.greenfox.sideproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tale_id")
    private Tale tale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="name", column = @Column(name="character_name"))
    })
    private UserCharacter userCharacter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="panel_id")
    private Panel panel;

    public Story() {
    }

    public Story(User user, UserCharacter userCharacter, Tale tale, Panel panel) {
        this.user = user;
        this.userCharacter = userCharacter;
        this.tale = tale;
        this.panel = panel;
    }
}
