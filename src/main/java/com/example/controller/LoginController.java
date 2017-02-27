package com.example.controller;

import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/barrage")
    public String barrage(){
        return "barrage";
    }

    @RequestMapping("/message")
    public String message(){
        return "webRTC";
    }

    @RequestMapping("/goo")
    public String goo(){
        return "goo";
    }

    @RequestMapping("/gooeyLoader")
    public String gooeyLoader(){
        return "gooeyLoader";
    }

    @RequestMapping("/learn")
    public String timeLine(){
        return "timeline";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

}
