package com.himansu.resumeanalyzer.repository;

import com.himansu.resumeanalyzer.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepo extends JpaRepository<Resume, Long> {

}