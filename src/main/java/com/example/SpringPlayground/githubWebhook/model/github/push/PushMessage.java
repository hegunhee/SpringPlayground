package com.example.SpringPlayground.githubWebhook.model.github.push;

import com.example.SpringPlayground.githubWebhook.model.github.*;
import com.example.SpringPlayground.githubWebhook.model.slack.SlackPayload;
import com.example.SpringPlayground.githubWebhook.model.slack.component.*;
import com.example.SpringPlayground.githubWebhook.model.slack.component.image.TextImage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PushMessage implements GithubMessage {

    private final Commits commits;

    private final GithubUser user;

    private final RepositoryInfo repo;

    @JsonCreator
    public PushMessage(
            @JsonProperty("commits") Commits commits,
            @JsonProperty("sender") GithubUser user,
            @JsonProperty("repository") RepositoryInfo repo
    ) {
        this.commits = commits;
        this.user = user;
        this.repo = repo;
    }

    @Override
    public SlackPayload toSlackPayload() {
        SlackPayload slackPayload = new SlackPayload();

        Header header = new Header(headerTitle());
        TextImage userImage = user.toTextImageComponent();
        Section commitSection = commits.toCommitTextSection();

        slackPayload.addComponent(header);
        slackPayload.addComponent(Divider.singleDivider);
        slackPayload.addComponent(userImage);
        slackPayload.addComponent(Divider.singleDivider);

        if (commitSection.fieldIsNotEmpty()) {
            slackPayload.addComponent(commitSection);
        }
        return slackPayload;
    }

    private String headerTitle() {
        return repo.getName() + "에서 Push 이벤트가 발생했습니다.";
    }
}
