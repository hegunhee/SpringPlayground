package com.example.SpringPlayground.githubWebhook.model.github.pullrequest;

import com.example.SpringPlayground.githubWebhook.model.github.GithubUser;
import com.example.SpringPlayground.githubWebhook.model.github.RepositoryInfo;
import com.example.SpringPlayground.githubWebhook.model.github.GithubMessage;
import com.example.SpringPlayground.githubWebhook.model.slack.SlackPayload;
import com.example.SpringPlayground.githubWebhook.model.slack.blocks.Divider;
import com.example.SpringPlayground.githubWebhook.model.slack.blocks.Header;
import com.example.SpringPlayground.githubWebhook.model.slack.blocks.Section;
import com.example.SpringPlayground.githubWebhook.model.slack.blocks.image.TextImage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PRMessage implements GithubMessage {

    private String action;

    @JsonProperty("pull_request")
    private PullRequest pullRequest;

    @JsonProperty("repository")
    private RepositoryInfo repo;

    @JsonProperty("html_url")
    private String url;

    @JsonProperty("sender")
    private GithubUser user;

    @Override
    public SlackPayload toSlackPayload() {
        SlackPayload slackPayload = new SlackPayload();

        Header header = new Header(repo.getName()+ "PullRequest가 "+ pullRequest.getState() + " 되었습니다.");
        Section prSection = pullRequest.toSectionBlock();
        TextImage userImage = user.toTextImageBlock();

        slackPayload.addComponent(header);
        slackPayload.addComponent(new Divider());
        slackPayload.addComponent(prSection);
        slackPayload.addComponent(new Divider());
        slackPayload.addComponent(userImage);

        return slackPayload;
    }
}
