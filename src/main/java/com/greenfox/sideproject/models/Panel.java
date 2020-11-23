package com.greenfox.sideproject.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Panel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tale_id")
    private Tale tale;

    @Column(length=1000000)
    @Lob
    private String mainText;

    @
    private List<Choice> choices;

    public Panel() {
    }

    public Panel(String mainText, Tale tale) {
        this.mainText = mainText;
        this.tale = tale;
    }
}
