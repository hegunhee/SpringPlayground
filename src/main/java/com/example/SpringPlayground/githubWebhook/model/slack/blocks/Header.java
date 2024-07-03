package com.example.SpringPlayground.githubWebhook.model.slack.blocks;

import lombok.Data;

@Data
public class Header implements Block {

    public Header(String text) {
        type = "header";
        this.text = new Text(text);
    }
    private String type;

    private Text text;
}
