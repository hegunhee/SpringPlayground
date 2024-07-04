package com.example.SpringPlayground.githubWebhook.model.github.push;

import com.example.SpringPlayground.githubWebhook.model.github.Commit;
import com.example.SpringPlayground.githubWebhook.model.github.GithubUser;
import com.example.SpringPlayground.githubWebhook.model.github.RepositoryInfo;
import com.example.SpringPlayground.githubWebhook.model.github.GithubMessage;
import com.example.SpringPlayground.githubWebhook.model.slack.SlackPayload;
import com.example.SpringPlayground.githubWebhook.model.slack.blocks.*;
import com.example.SpringPlayground.githubWebhook.model.slack.blocks.image.TextImage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PushMessage implements GithubMessage {

    @JsonProperty("commits")
    private List<Commit> commits;

    @JsonProperty("sender")
    private GithubUser user;

    @JsonProperty("repository")
    private RepositoryInfo repo;

    @Override
    public SlackPayload toSlackPayload() {
        SlackPayload slackPayload = new SlackPayload();

        Header header = new Header(repo.getName() + "에서 Push 이벤트가 발생했습니다.");
        TextImage userImage = user.toTextImageBlock();
        List<Text> commitPayload = commits.stream().map(Commit::toTextBlock).toList();
        Section commitSection = new Section(commitPayload);

        slackPayload.addBlock(header);
        slackPayload.addBlock(new Divider());
        slackPayload.addBlock(userImage);
        slackPayload.addBlock(new Divider());
        slackPayload.addBlock(commitSection);

        return slackPayload;
    }
}
