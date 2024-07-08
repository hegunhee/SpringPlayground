package com.example.SpringPlayground.githubWebhook.model.slack.component;

import lombok.Data;

@Data
public class Divider implements SlackPayloadComponent {

    public Divider() {
        this.type = "divider";
    }

    private String type;
}
