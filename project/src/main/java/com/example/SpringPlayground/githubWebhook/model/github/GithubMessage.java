package com.example.SpringPlayground.githubWebhook.model.github;

import com.example.SpringPlayground.githubWebhook.model.slack.SlackPayload;

public interface GithubMessage {

    SlackPayload toSlackPayload();
}
