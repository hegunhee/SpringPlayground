package com.example.SpringPlayground.githubWebhook.model.github;

import com.example.SpringPlayground.githubWebhook.model.slack.component.Text;
import com.example.SpringPlayground.githubWebhook.model.slack.component.image.Image;
import com.example.SpringPlayground.githubWebhook.model.slack.component.image.TextImage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * git webhook response에서 sender에 담겨서 넘어옴
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubUser {

    @JsonProperty("login")
    private String userName;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("html_url")
    private String url;

    public TextImage toTextImageBlock() {
        Text userText = new Text("보낸 사람: " +userName, url);
        Image image = new Image(avatarUrl, userName);
        return new TextImage(userText,image);
    }
}
