package com.example.SpringPlayground.githubWebhook.model.slack.component.image;

import com.example.SpringPlayground.githubWebhook.model.slack.component.SlackPayloadComponent;
import com.example.SpringPlayground.githubWebhook.model.slack.component.Text;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
public class TextImage implements SlackPayloadComponent {

    private final String type;

    private final Text text;

    @JsonProperty("accessory")
    private final Image image;

    public TextImage(Text text, Image image) {
        this.type = "section";
        this.text = text;
        this.image = image;
    }
}
