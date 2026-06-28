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

    private String cloudinaryPublicId;

    @Column(columnDefinition = "LONGTEXT")
    private String extractedText;

    @Column(columnDefinition = "LONGTEXT")
    private String analysisResult;

    public Resume() {
    }

    public Resume(String fullName,
                  String email,
                  String resumeFileName,
                  String resumePath,
                  String cloudinaryPublicId,
                  String extractedText,
                  String analysisResult) {

        this.fullName = fullName;
        this.email = email;
        this.resumeFileName = resumeFileName;
        this.resumePath = resumePath;
        this.cloudinaryPublicId = cloudinaryPublicId;
        this.extractedText = extractedText;
        this.analysisResult = analysisResult;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResumeFileName() {
        return resumeFileName;
    }

    public void setResumeFileName(String resumeFileName) {
        this.resumeFileName = resumeFileName;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    public String getCloudinaryPublicId() {
        return cloudinaryPublicId;
    }

    public void setCloudinaryPublicId(String cloudinaryPublicId) {
        this.cloudinaryPublicId = cloudinaryPublicId;
    }

    public String getExtractedText() {
        return extractedText;
    }

    public void setExtractedText(String extractedText) {
        this.extractedText = extractedText;
    }

    public String getAnalysisResult() {
        return analysisResult;
    }

    public void setAnalysisResult(String analysisResult) {
        this.analysisResult = analysisResult;
    }
}