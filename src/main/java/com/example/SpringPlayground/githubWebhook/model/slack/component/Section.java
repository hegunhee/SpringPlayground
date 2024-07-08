package com.example.SpringPlayground.githubWebhook.model.slack.component;

import lombok.Data;

import java.util.List;

@Data
public class Section implements SlackPayloadComponent {

    public Section(List<Text> fields) {
        this.type = "section";
        this.fields = fields;
    }

    private String type;

    private List<Text> fields;
}
