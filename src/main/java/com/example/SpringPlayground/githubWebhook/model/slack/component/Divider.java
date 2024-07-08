package com.example.SpringPlayground.githubWebhook.model.slack.component;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
public class Divider implements SlackPayloadComponent {

    public Divider() {
        this.type = "divider";
    }

    private String type;
}
