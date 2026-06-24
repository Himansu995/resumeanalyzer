package com.himansu.resumeanalyzer.service;

import org.springframework.stereotype.Service;

@Service
public class AnalysisService {

    public String analyze(String text)
    {
        StringBuilder result = new StringBuilder();

        int score = 0;

        if(text.toLowerCase().contains("java"))
        {
            result.append("Java Found\n");
            score += 20;
        }

        if(text.toLowerCase().contains("spring"))
        {
            result.append("Spring Found\n");
            score += 20;
        }

        if(text.toLowerCase().contains("mysql"))
        {
            result.append("MySQL Found\n");
            score += 20;
        }

        if(text.toLowerCase().contains("html"))
        {
            result.append("HTML Found\n");
            score += 20;
        }

        if(text.toLowerCase().contains("css"))
        {
            result.append("CSS Found\n");
            score += 20;
        }

        result.append("\nScore : ")
                .append(score)
                .append("/100");

        return result.toString();
    }
}