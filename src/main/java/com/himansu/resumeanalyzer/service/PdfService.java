package com.himansu.resumeanalyzer.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class PdfService {

    public String extractText(String filePath) {

        try {

            PDDocument document = Loader.loadPDF(new File(filePath));

            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            String text = pdfTextStripper.getText(document);

            document.close();

            return text;

        }
        catch (IOException e) {

            throw new RuntimeException("Unable to read PDF", e);

        }
    }
}