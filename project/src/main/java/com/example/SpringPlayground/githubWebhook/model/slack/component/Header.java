package com.example.SpringPlayground.githubWebhook.model.slack.component;

import lombok.*;

@Getter
public class Header implements SlackPayloadComponent {

    private final String type;

    private final Text text;

    public Header(String text) {
        type = "header";
        this.text = Text.createPlaneText(text);
    }
}