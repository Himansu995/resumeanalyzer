package com.himansu.resumeanalyzer.service;

import com.himansu.resumeanalyzer.entity.Resume;
import com.himansu.resumeanalyzer.repository.ResumeRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ResumeService {

    private final ResumeRepo resumeRepo;

    private final PdfService pdfService;

    private final AnalysisService analysisService;

    public ResumeService(
            ResumeRepo resumeRepo,
            PdfService pdfService,
            AnalysisService analysisService)
    {
        this.resumeRepo = resumeRepo;
        this.pdfService = pdfService;
        this.analysisService = analysisService;
    }

    public Resume saveResume(Resume resume)
    {
        return resumeRepo.save(resume);
    }

    public List<Resume> getAllResumes()
    {
        return resumeRepo.findAll();
    }

    public Resume uploadResume(
            MultipartFile file,
            String fullName,
            String email)
    {

        try
        {

            Path uploadPath = Paths.get("uploads");

            if(!Files.exists(uploadPath))
            {
                Files.createDirectories(uploadPath);
            }

            String uniqueFileName =
                    UUID.randomUUID() + "_"
                            + file.getOriginalFilename();

            Path filePath =
                    uploadPath.resolve(uniqueFileName);

            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            String extractedText =
                    pdfService.extractText(filePath.toString());

            String analysis =
                    analysisService.analyzeResume(extractedText);

            Resume resume = new Resume();

            resume.setFullName(fullName);

            resume.setEmail(email);

            resume.setResumeFileName(file.getOriginalFilename());

            resume.setResumePath(filePath.toString());

            resume.setExtractedText(extractedText);

            resume.setAnalysisResult(analysis);

            return resumeRepo.save(resume);

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

}