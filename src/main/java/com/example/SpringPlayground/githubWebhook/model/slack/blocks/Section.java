package com.example.SpringPlayground.githubWebhook.model.slack.blocks;

import lombok.Data;

import java.util.List;

@Data
public class Section implements Block{

    public Section(List<Text> fields) {
        this.type = "section";
        this.fields = fields;
    }

    private String type;

    private List<Text> fields;
}
