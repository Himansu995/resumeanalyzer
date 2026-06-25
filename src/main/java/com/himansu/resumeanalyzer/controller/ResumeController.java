package com.himansu.resumeanalyzer.controller;

import com.himansu.resumeanalyzer.entity.Resume;
import com.himansu.resumeanalyzer.service.AnalysisService;
import com.himansu.resumeanalyzer.service.PdfService;
import com.himansu.resumeanalyzer.service.ResumeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeService resumeService;
    private final PdfService pdfService;
    private final AnalysisService analysisService;

    public ResumeController(
            ResumeService resumeService,
            PdfService pdfService, AnalysisService analysisService)
    {
        this.resumeService = resumeService;
        this.pdfService = pdfService;
        this.analysisService=analysisService;
    }

    @PostMapping
    public Resume addResume(@RequestBody Resume resume)
    {
        return resumeService.saveResume(resume);
    }

    @PostMapping("/upload")
    public String uploadResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("fullName") String fullName,
            @RequestParam("email") String email,
            org.springframework.ui.Model model)
    {
        try
        {
            Path uploadPath = Paths.get("uploads");

            if(!Files.exists(uploadPath))
            {
                Files.createDirectories(uploadPath);
            }

            Path filePath =
                    uploadPath.resolve(file.getOriginalFilename());

            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            Resume resume = new Resume();

            resume.setFullName(fullName);
            resume.setEmail(email);
            resume.setResumeFileName(file.getOriginalFilename());
            resume.setResumePath(filePath.toString());
            String extractedText =
                    pdfService.extractText(
                            filePath.toString());

            resume.setExtractedText(extractedText);
            String analysis =
                    analysisService.analyze(extractedText);

            resume.setAnalysisResult(analysis);

            Resume savedResume = resumeService.saveResume(resume);

            model.addAttribute("resume", savedResume);

            return "result";
        }
        catch(Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<Resume> getAllResumes()
    {
        return resumeService.getAllResumes();
    }
}
