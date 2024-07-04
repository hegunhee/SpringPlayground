package com.example.SpringPlayground.githubWebhook.model.github;

import com.example.SpringPlayground.githubWebhook.model.slack.blocks.Section;
import com.example.SpringPlayground.githubWebhook.model.slack.blocks.Text;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryInfo {

    private String name;

    @JsonProperty("private")
    private boolean isPrivate;

    @JsonProperty("html_url")
    private String url;
}
