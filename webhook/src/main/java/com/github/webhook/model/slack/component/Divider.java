package com.github.webhook.model.slack.component;

import lombok.Getter;

@Getter
public class Divider implements SlackPayloadComponent {

    private final String type;

    public static final Divider singleDivider = new Divider();

    private Divider() {
        this.type = "divider";
    }
}
