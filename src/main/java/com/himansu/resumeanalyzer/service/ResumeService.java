package com.himansu.resumeanalyzer.service;

import com.himansu.resumeanalyzer.entity.Resume;
import com.himansu.resumeanalyzer.repository.ResumeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeService {

    private final ResumeRepo resumeRepo;

    public ResumeService(ResumeRepo resumeRepo)
    {
        this.resumeRepo = resumeRepo;
    }

    public Resume saveResume(Resume resume)
    {
        return resumeRepo.save(resume);
    }

    public List<Resume> getAllResumes()
    {
        return resumeRepo.findAll();
    }
}
