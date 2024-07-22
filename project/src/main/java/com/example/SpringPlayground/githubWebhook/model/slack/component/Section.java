package com.example.SpringPlayground.githubWebhook.model.slack.component;

import lombok.*;

import java.util.List;

@Getter
public class Section implements SlackPayloadComponent {

    private final String type;

    private final List<Text> fields;

    public Section(List<Text> fields) {
        this.type = "section";
        this.fields = fields;
    }

    public boolean fieldIsNotEmpty() {
        return !fields.isEmpty();
    }
}
