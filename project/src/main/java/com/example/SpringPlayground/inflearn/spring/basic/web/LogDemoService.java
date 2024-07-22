package com.example.SpringPlayground.inflearn.spring.basic.web;

import com.example.SpringPlayground.inflearn.spring.basic.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final MyLogger myLogger;
    public void logic(String id) {
        myLogger.log("id = " + id);
    }
}
