package com.himansu.resumeanalyzer.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class PdfService {

    public String extractText(String filePath)
    {
        try
        {
            File file = new File(filePath);

            PDDocument document =
                    Loader.loadPDF(file);

            PDFTextStripper pdfTextStripper =
                    new PDFTextStripper();

            String text =
                    pdfTextStripper.getText(document);

            document.close();

            return text;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}