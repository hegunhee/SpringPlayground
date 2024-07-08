package com.example.SpringPlayground.githubWebhook.model.github.ping;

import com.example.SpringPlayground.githubWebhook.model.github.RepositoryInfo;
import com.example.SpringPlayground.githubWebhook.model.github.GithubMessage;
import com.example.SpringPlayground.githubWebhook.model.slack.SlackPayload;
import com.example.SpringPlayground.githubWebhook.model.slack.blocks.Divider;
import com.example.SpringPlayground.githubWebhook.model.slack.blocks.Header;
import com.example.SpringPlayground.githubWebhook.model.slack.blocks.Section;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PingMessage implements GithubMessage {

    private Hook hook;

    @JsonProperty("repository")
    private RepositoryInfo repo;

    @Override
    public SlackPayload toSlackPayload() {
        SlackPayload slackPayload = new SlackPayload();
        Header pingHeader = new Header(repo.getName() + " 레포지토리에서 핑 이벤트가 발생했습니다.");
        Section hookSection = hook.toSection();

        slackPayload.addComponent(pingHeader);
        slackPayload.addComponent(new Divider());
        slackPayload.addComponent(hookSection);

        return slackPayload;
    }
}
