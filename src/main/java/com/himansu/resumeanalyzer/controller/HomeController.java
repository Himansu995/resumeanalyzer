package com.himansu.resumeanalyzer.controller;

import com.himansu.resumeanalyzer.service.ResumeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ResumeService resumeService;

    public HomeController(ResumeService resumeService)
    {
        this.resumeService = resumeService;
    }

    @GetMapping("/")
    public String home()
    {
        return "index";
    }

    @GetMapping("/resumes")
    public String showResumes(Model model)
    {
        model.addAttribute("resumes", resumeService.getAllResumes());

        return "resumes";
    }
}