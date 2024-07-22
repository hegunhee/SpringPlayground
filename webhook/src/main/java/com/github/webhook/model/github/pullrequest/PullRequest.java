package com.github.webhook.model.github.pullrequest;

import com.github.webhook.model.slack.component.Section;
import com.github.webhook.model.slack.component.Text;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PullRequest {

    private final String url;

    private final String state;

    private final String title;

    private final String body;

    @JsonCreator
    public PullRequest(
        @JsonProperty("html_url") String url,
        @JsonProperty("state") String state,
        @JsonProperty("title") String title,
        @JsonProperty("body") String body
    ) {
        this.url = url;
        this.state = state;
        this.title = title;
        this.body = body;
    }

    public Section toSection() {
        Text prText = Text.createMarkdownText(title, url);
        return new Section(List.of(prText));
    }
}

