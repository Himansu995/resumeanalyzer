package com.himansu.resumeanalyzer.entity;

import jakarta.persistence.*;

@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String email;

    private String resumeFileName;

    private String resumePath;

    private String analysisResult;

    @Column(columnDefinition = "TEXT")
    private String extractedText;

    public Resume()
    {
    }

    public Resume(String fullName, String email, String resumeFileName, String resumePath, String analysisResult, String extractedText)
    {
        this.fullName = fullName;
        this.email = email;
        this.resumeFileName=resumeFileName;
        this.resumePath = resumePath;
        this.analysisResult=analysisResult;
        this.extractedText=extractedText;
    }

    public Long getId()
    {
        return id;
    }

    public String getFullName()
    {
        return fullName;
    }

    public String getExtractedText() {
        return extractedText;
    }

    public void setExtractedText(String extractedText) {
        this.extractedText = extractedText;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getResumeFileName() {
        return resumeFileName;
    }

    public String getAnalysisResult() {
        return analysisResult;
    }

    public void setAnalysisResult(String analysisResult) {
        this.analysisResult = analysisResult;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    public void setResumeFileName(String resumeFileName) {
        this.resumeFileName = resumeFileName;
    }
}
