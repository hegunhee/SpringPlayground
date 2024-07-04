package com.example.SpringPlayground.githubWebhook.controller;

import com.example.SpringPlayground.githubWebhook.model.github.GithubMessage;
import com.example.SpringPlayground.githubWebhook.model.github.ping.PingMessage;
import com.example.SpringPlayground.githubWebhook.model.slack.SlackPayload;
import com.example.SpringPlayground.githubWebhook.service.SlackService;
import com.example.SpringPlayground.githubWebhook.model.github.pullrequest.PRMessage;
import com.example.SpringPlayground.githubWebhook.model.github.push.PushMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final ObjectMapper mapper = new ObjectMapper();



    @PostMapping("/push")
    public ResponseEntity<String> push(
            @RequestHeader Map<String, String> headers,
            @RequestBody String requestBody
    ) throws JsonProcessingException {
        String eventType = headers.get("x-github-event");
        GithubMessage githubMessage;
        SlackPayload payload;
        try {
            if(isPingEvent(eventType)) {
                githubMessage = mapper.readValue(requestBody,PingMessage.class);
            }else {
                githubMessage = mapper.readValue(requestBody,PushMessage.class);
            }
            payload = githubMessage.toSlackPayload();
        } catch(JsonProcessingException e) {
            log.error("github 응답 json 파싱 실패 ={}",e.toString());
            return new ResponseEntity("jsonParseError", HttpStatus.NOT_FOUND);
        }
        try {
            slackService.sendSlackNotification(payload);
            return new ResponseEntity("Ok",HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity("Error",HttpStatus.NOT_FOUND);
        }
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
            if(isPingEvent(eventType)) {
                githubMessage = mapper.readValue(requestBody,PingMessage.class);
            }else {
                githubMessage = mapper.readValue(requestBody,PRMessage.class);
            }
            payload = githubMessage.toSlackPayload();
        } catch(JsonProcessingException e) {
            log.error("github 응답 json 파싱 실패 ={}",e.toString());
            return new ResponseEntity("jsonParseError", HttpStatus.NOT_FOUND);
        }
        try {
            slackService.sendSlackNotification(payload);
            return new ResponseEntity("Ok",HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity("Error",HttpStatus.NOT_FOUND);
        }
    }

    private boolean isPingEvent(String eventHeader) {
        if(eventHeader.equals("ping")) {
            return true;
        }else {
            return false;
        }
    }
}