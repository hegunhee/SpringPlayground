package com.example.SpringPlayground.githubWebhook.model.slack.blocks;

import lombok.Data;

@Data
public class Divider implements Block{

    public Divider() {
        this.type = "divider";
    }
    private String type;
}
