package com.himansu.resumeanalyzer.controller;

import com.himansu.resumeanalyzer.entity.Resume;
import com.himansu.resumeanalyzer.service.ResumeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/upload")
    public String uploadPage()
    {
        return "upload";
    }

    @PostMapping("/uploadResume")
    public String uploadResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("fullName") String fullName,
            @RequestParam("email") String email,
            Model model)
    {
        Resume resume = resumeService.uploadResume(file, fullName, email);

        model.addAttribute("resume", resume);

        return "result";
    }
}