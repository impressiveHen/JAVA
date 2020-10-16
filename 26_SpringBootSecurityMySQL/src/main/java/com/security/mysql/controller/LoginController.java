package com.security.mysql.controller;

import com.security.mysql.entity.User;
import com.security.mysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value={"/", "/login"}, method= RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }

    @RequestMapping(value="/registration", method=RequestMethod.GET)
    public String registration(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    // Spring BindingResult tutorial shows how to use BindingResult to get the result of a validation.
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String createNewUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, ModelMap model) {
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            /*
            rejectValue(field, errorCode, defaultMessage): Register a field error for the specified
            field of the current object:
            */
            bindingResult.rejectValue("userName", "error.user", "*User name already exists");
        }

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.saveUser(user, "ADMIN");
        model.addAttribute("successMessage", "User has been successfully");
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value="/admin/home", method=RequestMethod.GET)
    public String adminHome(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("greetings", "Welcome "+user.getUserName()+" / "+user.getFirstName()+" "+user.getLastName()+" / "+user.getEmail());
        model.addAttribute("adminMessage","Content Available Only for Users with Admin Role");
        return "admin/home";
    }

    @RequestMapping(value="/user/home", method=RequestMethod.GET)
    public String userHome(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        model.addAttribute("greetings", "Welcome "+user.getUserName()+" / "+user.getFirstName()+" "+user.getLastName()+" / "+user.getEmail());
        model.addAttribute("userMessage","You are user role");
        return "user/home";
    }
}
