package com.example.SpringPlayground.inflearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InflearnController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","hello!!");
        return "hello";
    }

    /*
    hello-mvc는 url의 path가 되고
    path = 슬래시로 이어지는 주소
    param은 key=value
    hello-template 가 html 이름이랑 매핑됨
    즉 return value와 맞는 html을 뱉음
     */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name,Model model) {
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name; // "hello spring" view가 없이 해당 문자 그대로 표현됨
        // 데이터를 그대로 표현됨
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        // Return 값이 객체라면 Json 형태로 반환해줌
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
