package com.example.SpringPlayground.githubWebhook.model.slack.blocks;

import lombok.Data;

@Data
public class Divider implements SlackPayloadComponent {

    public Divider() {
        this.type = "divider";
    }

    private String type;
}
