package com.himansu.resumeanalyzer.controller;

import com.himansu.resumeanalyzer.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage()
    {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String password,
            Model model)
    {

        try
        {

            userService.registerUser(
                    fullName,
                    email,
                    password
            );

            return "redirect:/login";

        }
        catch (Exception e)
        {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            return "register";

        }

    }

    @GetMapping("/login")
    public String loginPage()
    {
        return "login";
    }

}