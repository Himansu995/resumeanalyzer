package com.himansu.resumeanalyzer.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalysisService {

    public String analyzeResume(String extractedText)
    {
        String text = extractedText.toLowerCase();

        List<String> foundSkills = new ArrayList<>();

        int score = 0;

        String[] skills = {
                "java",
                "spring",
                "spring boot",
                "mysql",
                "html",
                "css",
                "javascript",
                "python",
                "c++",
                "hibernate",
                "jpa",
                "bootstrap",
                "git",
                "github"
        };

        for(String skill : skills)
        {
            if(text.contains(skill))
            {
                foundSkills.add(skill);
                score += 5;
            }
        }

        StringBuilder analysis = new StringBuilder();

        analysis.append("===== Resume Analysis =====\n\n");

        analysis.append("Skills Found\n");

        if(foundSkills.isEmpty())
        {
            analysis.append("No matching skills found.\n");
        }
        else
        {
            for(String skill : foundSkills)
            {
                analysis.append("✔ ")
                        .append(skill)
                        .append("\n");
            }
        }

        analysis.append("\n");

        analysis.append("Resume Score : ")
                .append(score)
                .append("/100\n");

        if(score >= 60)
        {
            analysis.append("\nExcellent Resume");
        }
        else if(score >= 40)
        {
            analysis.append("\nGood Resume");
        }
        else
        {
            analysis.append("\nNeeds Improvement");
        }

        return analysis.toString();
    }
}