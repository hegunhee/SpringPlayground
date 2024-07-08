package com.example.SpringPlayground.githubWebhook.model.slack.component.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Image {

    public Image(String imageUrl, String altText) {
        this.type = "image";
        this.imageUrl = imageUrl;
        this.altText = altText;
    }
    private String type;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("alt_text")
    private String altText;
}
