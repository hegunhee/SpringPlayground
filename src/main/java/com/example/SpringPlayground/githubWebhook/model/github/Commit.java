package com.example.SpringPlayground.githubWebhook.model.github;

import com.example.SpringPlayground.githubWebhook.model.slack.component.Text;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Commit {

    private String url;

    @JsonProperty("timestamp")
    private String timeStamp;

    private String message;

    public Text toTextBlock() {
        return new Text(message,url);
    }
}
