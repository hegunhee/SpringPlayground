package com.example.SpringPlayground.githubWebhook.model.slack.component.image;

import com.example.SpringPlayground.githubWebhook.model.slack.component.SlackPayloadComponent;
import com.example.SpringPlayground.githubWebhook.model.slack.component.Text;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TextImage implements SlackPayloadComponent {

    public TextImage(Text text, Image image) {
        this.type = "section";
        this.text = text;
        this.image = image;
    }
    private String type;

    private Text text;

    @JsonProperty("accessory")
    private Image image;
}
