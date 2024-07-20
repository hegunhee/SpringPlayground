package com.example.SpringPlayground.githubWebhook.model.slack.component;

import lombok.*;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Section implements SlackPayloadComponent {

    public Section(List<Text> fields) {
        this.type = "section";
        this.fields = fields;
    }

    private String type;

    private List<Text> fields;

    public boolean fieldIsNotEmpty() {
        return !fields.isEmpty();
    }
}
