package com.example.SpringPlayground.githubWebhook.model.github;

import com.example.SpringPlayground.githubWebhook.model.slack.component.Text;
import com.example.SpringPlayground.githubWebhook.model.slack.component.image.Image;
import com.example.SpringPlayground.githubWebhook.model.slack.component.image.TextImage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * git webhook response에서 sender에 담겨서 넘어옴
 */
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubUser {

    private final String userName;

    private final String avatarUrl;

    private final String url;

    @JsonCreator
    public GithubUser(
            @JsonProperty("login") String userName,
            @JsonProperty("avatar_url") String avatarUrl,
            @JsonProperty("html_url") String url
    ) {
        this.userName = userName;
        this.avatarUrl = avatarUrl;
        this.url = url;
    }

    public TextImage toTextImageComponent() {
        String textTitle = "보낸 사람 : " + userName;

        Text userText = Text.createMarkdownText(textTitle, url);
        Image userImage = new Image(avatarUrl, userName);

        return new TextImage(userText,userImage);
    }
}
