package com.github.webhook.model.github;

import com.github.webhook.model.slack.SlackPayload;

public interface GithubMessage {

    SlackPayload toSlackPayload();
}
