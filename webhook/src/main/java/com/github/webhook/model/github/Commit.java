package com.github.webhook.model.github;

import com.github.webhook.model.slack.component.Text;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Commit {

    private final String url;

    private final String timeStamp;

    private final String message;

    @JsonCreator
    public Commit(
            @JsonProperty("url") String url,
            @JsonProperty("timestamp") String timeStamp,
            @JsonProperty("message") String message
    ) {
        this.url = url;
        this.timeStamp = timeStamp;
        this.message = message;
    }

    public Text toTextComponent() {
        return Text.createMarkdownText(message,url);
    }
}
