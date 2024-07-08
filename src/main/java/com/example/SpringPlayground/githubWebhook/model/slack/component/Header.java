package com.example.SpringPlayground.githubWebhook.model.slack.component;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Header implements SlackPayloadComponent {

    public Header(String text) {
        type = "header";
        this.text = new Text(text);
    }
    private String type;

    private Text text;
}
