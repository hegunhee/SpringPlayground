package com.github.webhook.model.github.ping;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.webhook.model.github.GithubMessage;
import com.github.webhook.model.github.RepositoryInfo;
import com.github.webhook.model.slack.SlackPayload;
import com.github.webhook.model.slack.component.Divider;
import com.github.webhook.model.slack.component.Header;
import com.github.webhook.model.slack.component.Section;
import com.github.webhook.model.slack.component.SlackPayloadComponent;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PingMessage implements GithubMessage {

    private final Hook hook;

    private final RepositoryInfo repo;

    @JsonCreator
    public PingMessage(
            @JsonProperty("hook") Hook hook,
            @JsonProperty("repository") RepositoryInfo repo
    ) {
        this.hook = hook;
        this.repo = repo;
    }

    @Override
    public SlackPayload toSlackPayload() {
        List<SlackPayloadComponent> components = new ArrayList<>();

        String headerTitle = repo.getName() + "레포지토리에서 핑 이벤트가 발생했습니다.";
        Header pingHeader = new Header(headerTitle);
        Section hookSection = hook.toSection();

        components.add(pingHeader);
        components.add(Divider.singleDivider);
        components.add(hookSection);

        return new SlackPayload(components);
    }
}
