package com.example.SpringPlayground.githubWebhook.model.github.pullrequest;

import com.example.SpringPlayground.githubWebhook.model.github.GithubUser;
import com.example.SpringPlayground.githubWebhook.model.github.RepositoryInfo;
import com.example.SpringPlayground.githubWebhook.model.github.GithubMessage;
import com.example.SpringPlayground.githubWebhook.model.slack.SlackPayload;
import com.example.SpringPlayground.githubWebhook.model.slack.component.Divider;
import com.example.SpringPlayground.githubWebhook.model.slack.component.Header;
import com.example.SpringPlayground.githubWebhook.model.slack.component.Section;
import com.example.SpringPlayground.githubWebhook.model.slack.component.image.TextImage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PRMessage implements GithubMessage {

    private final String action;

    private final PullRequest pullRequest;

    private final RepositoryInfo repo;

    private final String url;

    private final GithubUser user;

    @JsonCreator
    public PRMessage(
            @JsonProperty("action") String action,
            @JsonProperty("pull_request") PullRequest pullRequest,
            @JsonProperty("repository") RepositoryInfo repo,
            @JsonProperty("html_url") String url,
            @JsonProperty("sender") GithubUser user
    ) {
        this.action = action;
        this.pullRequest = pullRequest;
        this.repo = repo;
        this.url = url;
        this.user = user;
    }

    @Override
    public SlackPayload toSlackPayload() {
        SlackPayload slackPayload = new SlackPayload();

        String headerTitle = repo.getName() + "PullRequest가 " + pullRequest.getState() + " 되었습니다.";
        Header header = new Header(headerTitle);
        Section prSection = pullRequest.toSection();
        TextImage userImage = user.toTextImageComponent();

        slackPayload.addComponent(header);
        slackPayload.addComponent(Divider.singleDivider);
        slackPayload.addComponent(prSection);
        slackPayload.addComponent(Divider.singleDivider);
        slackPayload.addComponent(userImage);

        return slackPayload;
    }
}
