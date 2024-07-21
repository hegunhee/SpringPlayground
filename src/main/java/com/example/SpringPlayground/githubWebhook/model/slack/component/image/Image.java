package com.example.SpringPlayground.githubWebhook.model.slack.component.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
public class Image {

    private final String type;

    @JsonProperty("image_url")
    private final String imageUrl;

    @JsonProperty("alt_text")
    private final String altText;

    public Image(String imageUrl, String altText) {
        this.type = "image";
        this.imageUrl = imageUrl;
        this.altText = altText;
    }
}
