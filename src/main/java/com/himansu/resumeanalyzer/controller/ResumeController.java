package com.himansu.resumeanalyzer.controller;

import com.himansu.resumeanalyzer.entity.Resume;
import com.himansu.resumeanalyzer.service.ResumeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping
    public Resume addResume(@RequestBody Resume resume) {
        return resumeService.saveResume(resume);
    }

    @GetMapping
    public List<Resume> getAllResumes() {
        return resumeService.getAllResumes();
    }
}