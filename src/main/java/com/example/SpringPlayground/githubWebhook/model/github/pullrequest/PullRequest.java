package com.example.SpringPlayground.githubWebhook.model.github.pullrequest;

import com.example.SpringPlayground.githubWebhook.model.slack.blocks.Section;
import com.example.SpringPlayground.githubWebhook.model.slack.blocks.Text;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PullRequest {

    @JsonProperty("html_url")
    private String url;

    private String state;

    private String title;

    private String body;

    public Section toSectionBlock() {
        Text prText = new Text(title, url);
        Section prSection = new Section(List.of(prText));
        return prSection;
    }
}

