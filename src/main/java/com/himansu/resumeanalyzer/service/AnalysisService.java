package com.himansu.resumeanalyzer.service;

import org.springframework.stereotype.Service;

@Service
public class AnalysisService {

    private final GeminiService geminiService;

    public AnalysisService(GeminiService geminiService)
    {
        this.geminiService = geminiService;
    }

    public String analyzeResume(String extractedText)
    {
        return geminiService.analyzeResume(extractedText);
    }
}