package com.example.SpringPlayground.githubWebhook.model.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryInfo {

    private String name;

    @JsonProperty("private")
    private boolean isPrivate;

    @JsonProperty("html_url")
    private String url;
}
