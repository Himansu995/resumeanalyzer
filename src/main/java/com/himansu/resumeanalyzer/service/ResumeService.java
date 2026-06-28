package com.himansu.resumeanalyzer.service;

import com.himansu.resumeanalyzer.entity.Resume;
import com.himansu.resumeanalyzer.repository.ResumeRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class ResumeService {

    private final ResumeRepo resumeRepo;

    private final PdfService pdfService;

    private final AnalysisService analysisService;

    private final CloudinaryService cloudinaryService;

    public ResumeService(
            ResumeRepo resumeRepo,
            PdfService pdfService,
            AnalysisService analysisService,
            CloudinaryService cloudinaryService)
    {
        this.resumeRepo = resumeRepo;
        this.pdfService = pdfService;
        this.analysisService = analysisService;
        this.cloudinaryService = cloudinaryService;
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

            String extractedText =
                    pdfService.extractText(file);

            String analysis =
                    analysisService.analyzeResume(extractedText);

            Map<String, String> cloudinaryResult =
                    cloudinaryService.uploadFile(file);

            Resume resume = new Resume();

            resume.setFullName(fullName);

            resume.setEmail(email);

            resume.setResumeFileName(file.getOriginalFilename());

            resume.setResumePath(
                    cloudinaryResult.get("url")
            );

            resume.setCloudinaryPublicId(
                    cloudinaryResult.get("publicId")
            );

            resume.setExtractedText(extractedText);

            resume.setAnalysisResult(analysis);

            return resumeRepo.save(resume);

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    public void deleteResume(Long id)
    {

        Resume resume = resumeRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Resume not found"));

        cloudinaryService.deleteFile(
                resume.getCloudinaryPublicId()
        );

        resumeRepo.delete(resume);

    }

    public Resume getResumeById(Long id)
    {
        return resumeRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Resume not found"));
    }

    public List<Resume> searchResume(String keyword)
    {
        return resumeRepo
                .findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                        keyword,
                        keyword
                );
    }

}