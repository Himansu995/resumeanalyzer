package com.himansu.resumeanalyzer.controller;

import com.himansu.resumeanalyzer.entity.Resume;
import com.himansu.resumeanalyzer.service.AnalysisService;
import com.himansu.resumeanalyzer.service.GeminiService;
import com.himansu.resumeanalyzer.service.PdfService;
import com.himansu.resumeanalyzer.service.ResumeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    private final GeminiService geminiService;

    private final PdfService pdfService;
    private final AnalysisService analysisService;

    public ResumeController(
            ResumeService resumeService,
            PdfService pdfService,
            AnalysisService analysisService,
            GeminiService geminiService)
    {
        this.resumeService = resumeService;
        this.pdfService = pdfService;
        this.analysisService = analysisService;
        this.geminiService = geminiService;
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