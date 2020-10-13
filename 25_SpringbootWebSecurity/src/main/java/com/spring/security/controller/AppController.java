package com.spring.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AppController {
    @RequestMapping(value={"/", "/home"}, method= RequestMethod.GET)
    public String home(ModelMap model) {
        return "home";
    }

    @RequestMapping(value={"/hello"}, method= RequestMethod.GET)
    public String hello(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping(value={"/login"}, method= RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }
}
