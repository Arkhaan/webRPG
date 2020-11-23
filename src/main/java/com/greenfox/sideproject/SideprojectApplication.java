package com.greenfox.sideproject;

import com.greenfox.sideproject.models.Panel;
import com.greenfox.sideproject.services.PanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SideprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SideprojectApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        PanelService panelService;
//        panelService.savePanel(new Panel("Welcome to the Darkness and Light adventure. Set in a fantasy world, you will embody a human character and decide on your fate.\n\nBut first, what is your gender?"));
//    }
}
