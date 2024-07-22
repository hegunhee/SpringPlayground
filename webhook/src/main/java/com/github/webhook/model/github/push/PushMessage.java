package com.github.webhook.model.github.push;

import com.github.webhook.model.github.*;
import com.github.webhook.model.slack.SlackPayload;
import com.github.webhook.model.slack.component.*;
import com.github.webhook.model.slack.component.image.TextImage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
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
        List<SlackPayloadComponent> components = new ArrayList<>();

        Header header = new Header(headerTitle());
        TextImage userImage = user.toTextImageComponent();
        Section commitSection = commits.toCommitTextSection();


        components.add(header);
        components.add(Divider.singleDivider);
        components.add(userImage);
        components.add(Divider.singleDivider);

        if (commitSection.fieldIsNotEmpty()) {
            components.add(commitSection);
        }
        return new SlackPayload(components);
    }

    private String headerTitle() {
        return repo.getName() + "에서 Push 이벤트가 발생했습니다.";
    }
}
