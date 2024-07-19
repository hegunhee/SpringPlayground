package com.example.SpringPlayground.githubWebhook.model.slack.component;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Divider implements SlackPayloadComponent {

    private final String type;

    public static final Divider singleDivider = new Divider();

    private Divider() {
        this.type = "divider";
    }
}
