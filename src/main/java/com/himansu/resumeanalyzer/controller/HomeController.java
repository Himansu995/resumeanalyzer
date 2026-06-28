package com.himansu.resumeanalyzer.controller;

import com.himansu.resumeanalyzer.entity.Resume;
import com.himansu.resumeanalyzer.service.ResumeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

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
    public String showResumes(
            @RequestParam(required = false) String keyword,
            Model model)
    {

        if (keyword == null || keyword.isBlank())
        {
            model.addAttribute(
                    "resumes",
                    resumeService.getAllResumes());
        }
        else
        {
            model.addAttribute(
                    "resumes",
                    resumeService.searchResume(keyword));
        }

        model.addAttribute("keyword", keyword);

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

        Resume resume = resumeService.uploadResume(
                file,
                fullName,
                email
        );

        model.addAttribute("resume", resume);

        return "result";
    }

    @GetMapping("/delete/{id}")
    public String deleteResume(@PathVariable Long id)
    {

        resumeService.deleteResume(id);

        return "redirect:/resumes";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadResume(
            @PathVariable Long id)
    {
        try
        {
            Resume resume = resumeService.getResumeById(id);

            Resource resource =
                    new UrlResource(resume.getResumePath());

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" +
                                    resume.getResumeFileName() + "\"")
                    .header(
                            HttpHeaders.CONTENT_TYPE,
                            "application/pdf")
                    .body(resource);

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/resume/{id}")
    public String viewResume(
            @PathVariable Long id,
            Model model)
    {

        Resume resume = resumeService.getResumeById(id);

        model.addAttribute("resume", resume);

        return "details";
    }

}