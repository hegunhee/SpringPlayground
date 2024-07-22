package com.github.webhook.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.webhook.mapper.GithubMessageMapper;
import com.github.webhook.model.github.GithubMessage;
import com.github.webhook.model.slack.SlackPayload;
import com.github.webhook.service.SlackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("github-webhook")
@Slf4j
public class GitHubWebhookController {

    private final SlackService slackService;

    private final GithubMessageMapper githubMessageMapper;

    @PostMapping("/push")
    public ResponseEntity<String> push(
            @RequestHeader Map<String, String> headers,
            @RequestBody String requestBody) {
        String eventType = headers.get("x-github-event");
        GithubMessage githubMessage;
        SlackPayload payload;
        try {
            githubMessage = githubMessageMapper.toGithubMessage(requestBody, eventType);
            payload = githubMessage.toSlackPayload();
            slackService.sendSlackNotification(payload);
        } catch (JsonProcessingException e) {
            log.error("github 응답 json 파싱 실패 ={}", e.toString());
            return new ResponseEntity<>("jsonParseError", HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Slack Error", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Ok");
    }

    @PostMapping("/pull-request")
    public ResponseEntity<String> pullRequest(
            @RequestHeader Map<String, String> headers,
            @RequestBody String requestBody
    ) {
        String eventType = headers.get("x-github-event");
        GithubMessage githubMessage;
        SlackPayload payload;
        try {
            githubMessage = githubMessageMapper.toGithubMessage(requestBody, eventType);
            payload = githubMessage.toSlackPayload();
            slackService.sendSlackNotification(payload);
        } catch (JsonProcessingException e) {
            log.error("github 응답 json 파싱 실패 ={}", e.toString());
            return new ResponseEntity<>("jsonParseError", HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Slack Error", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Ok");
    }
}