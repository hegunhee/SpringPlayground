package com.example.SpringPlayground.githubWebhook.model.github.push;

import com.example.SpringPlayground.githubWebhook.model.github.Commit;
import com.example.SpringPlayground.githubWebhook.model.github.GithubUser;
import com.example.SpringPlayground.githubWebhook.model.github.RepositoryInfo;
import com.example.SpringPlayground.githubWebhook.model.github.GithubMessage;
import com.example.SpringPlayground.githubWebhook.model.slack.SlackPayload;
import com.example.SpringPlayground.githubWebhook.model.slack.component.*;
import com.example.SpringPlayground.githubWebhook.model.slack.component.image.TextImage;
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

    // 만약 commit이 없는 빈 push라면 slack에 메시지를 전달하지 않음
    @Override
    public SlackPayload toSlackPayload() {
        SlackPayload slackPayload = new SlackPayload();
        if(commits.isEmpty()) {
            return slackPayload;
        }

        Header header = new Header(repo.getName() + "에서 Push 이벤트가 발생했습니다.");
        TextImage userImage = user.toTextImageBlock();
        List<Text> commitPayload = commits.stream().map(Commit::toTextBlock).toList();
        Section commitSection = new Section(commitPayload);

        slackPayload.addComponent(header);
        slackPayload.addComponent(new Divider());
        slackPayload.addComponent(userImage);
        slackPayload.addComponent(new Divider());
        slackPayload.addComponent(commitSection);

        return slackPayload;
    }
}
