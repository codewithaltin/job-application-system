package com.example.careerify.service;

import com.example.careerify.common.dto.JobPostingResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface JobPostingService {

    JobPostingResponseDTO createJobPosting(JobPostingResponseDTO jobPostingResponseDTO);
    JobPostingResponseDTO createJobPostingForEmployeer(UUID employeerId, JobPostingResponseDTO jobPostingResponseDTO);

    JobPostingResponseDTO getJobPostingById(Long jobPostingId);

    void updateJobPosting(Long jobPostingId, JobPostingResponseDTO jobPostingResponseDTO);

    void deleteJobPosting(Long jobPostingId);

    Page<JobPostingResponseDTO> getAllJobPostings(Pageable pageable);

    List<JobPostingResponseDTO> getAllJobPostingsByEmployerId(UUID employerId);

    List<JobPostingResponseDTO> getJobPostingsByCompanyName(String companyName);
    List<JobPostingResponseDTO> getJobPostingsByPostDateAfter(LocalDate postDate);

    List<JobPostingResponseDTO> getJobPostingsBySalaryGreaterThan(float salary);

    List<JobPostingResponseDTO> getJobPostingsByTitle(String keyword);

}
