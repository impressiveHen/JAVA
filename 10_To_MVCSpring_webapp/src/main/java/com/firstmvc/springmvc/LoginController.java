package com.firstmvc.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

// @Controller, appose to servlet extends HttpServlet
//  defined in web.xml, localhost:8080/spring-mvc/ goes to the Spring Dispatcher Servlet
@Controller
public class LoginController {
    // Set the login service - Spring Auto Wiring (dependency injection)
    @Autowired
    LoginService service;

    // http://localhost:8080/spring-mvc/hello
    @RequestMapping(path="/hello")
    // @ResponseBody is a Spring annotation which binds a method return value to
    // the web response body, based on the content-type in the request HTTP header.
    @ResponseBody
    public String hello() {
        return "hi";
    }

    // http://localhost:8080/spring-mvc/login
    // View resolver snippet added in todo-servlet.xml
    // map login -> /WEB-INF/views/login.jsp
    // prefix -> /WEB-INF/views
    // suffix -> .jsp
    @RequestMapping(path="/login", method=RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }

    // /WEB-INF/views/welcome.jsp
    // @RequestParam get post arguments
    // ModelMap model allows data flow between controller <-> view
    @RequestMapping(path="/login", method=RequestMethod.POST)
    public String handleLoginRequest(
            @RequestParam String name,
            @RequestParam String password,
            ModelMap model) {


        if (service.validateUser(name, password)) {
            model.put("name", name);
            model.put("password", password);
            return "welcome";
        }

        model.put("errorMessage", "invalid credentials");
        return "login";
    }
}

