package com.example.careerify.service;

import com.example.careerify.common.dto.JobPostingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface JobPostingService {

    JobPostingDTO createJobPosting(JobPostingDTO jobPostingDTO);
    JobPostingDTO createJobPostingForEmployeer(UUID employeerId, JobPostingDTO jobPostingDTO);

    JobPostingDTO getJobPostingById(Long jobPostingId);

    void updateJobPosting(Long jobPostingId, JobPostingDTO jobPostingDTO);

    void deleteJobPosting(Long jobPostingId);

    Page<JobPostingDTO> getAllJobPostings(Pageable pageable);

    List<JobPostingDTO> getAllJobPostingsByEmployerId(UUID employerId);
}
