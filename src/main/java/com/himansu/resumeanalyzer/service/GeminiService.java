package com.himansu.resumeanalyzer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String analyzeResume(String resumeText) {

        try {

            String prompt = """
                    You are an expert ATS Resume Reviewer.

                    Analyze the following resume.

                    Return the response in this exact format:

                    ATS Score: xx/100

                    Strengths:
                    - ...

                    Weaknesses:
                    - ...

                    Missing Skills:
                    - ...

                    Suggested Improvements:
                    - ...

                    HR Recommendation:
                    - ...

                    Resume:

                    """ + resumeText;

            String requestBody = """
                    {
                      "contents": [
                        {
                          "parts": [
                            {
                              "text": %s
                            }
                          ]
                        }
                      ]
                    }
                    """.formatted(
                    objectMapper.writeValueAsString(prompt)
            );

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity =
                    new HttpEntity<>(requestBody, headers);

            String url =
                    "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
                            + apiKey;

            ResponseEntity<String> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            entity,
                            String.class
                    );

            JsonNode json =
                    objectMapper.readTree(response.getBody());

            return json
                    .get("candidates")
                    .get(0)
                    .get("content")
                    .get("parts")
                    .get(0)
                    .get("text")
                    .asText();

        }
        catch (Exception e) {

            throw new RuntimeException("Gemini API Error", e);

        }

    }

}