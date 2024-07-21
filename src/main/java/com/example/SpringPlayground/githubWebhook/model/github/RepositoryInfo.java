package com.example.SpringPlayground.githubWebhook.model.github;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryInfo {

    private final String name;

    @JsonProperty("private")
    private final boolean isPrivate;

    @JsonProperty("html_url")
    private final String url;

    @JsonCreator
    public RepositoryInfo(
            @JsonProperty("name") String name,
            @JsonProperty("private") boolean isPrivate,
            @JsonProperty("html_url") String url
    ) {
        this.name = name;
        this.isPrivate = isPrivate;
        this.url = url;
    }
}
