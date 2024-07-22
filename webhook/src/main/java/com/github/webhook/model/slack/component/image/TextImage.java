package com.github.webhook.model.slack.component.image;

import com.github.webhook.model.slack.component.SlackPayloadComponent;
import com.github.webhook.model.slack.component.Text;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
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
