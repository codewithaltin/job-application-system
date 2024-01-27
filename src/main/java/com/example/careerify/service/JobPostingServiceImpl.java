package com.example.careerify.service;

import com.example.careerify.common.dto.JobPostingDTO;
import com.example.careerify.common.mappers.JobPostingMapper;
import com.example.careerify.model.Employeer;
import com.example.careerify.model.JobPosting;
import com.example.careerify.repository.EmployeerRepository;
import com.example.careerify.repository.JobPostingRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JobPostingServiceImpl implements JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final JobPostingMapper jobPostingMapper;
    private final EmployeerRepository employeerRepository;

    public JobPostingServiceImpl(JobPostingRepository jobPostingRepository, JobPostingMapper jobPostingMapper,EmployeerRepository employeerRepository){
        this.jobPostingRepository = jobPostingRepository;
        this.jobPostingMapper = jobPostingMapper;
        this.employeerRepository = employeerRepository;
    }
    @Override
    public Page<JobPostingDTO> getAllJobPostings(Pageable pageable) {
        Page<JobPosting> jobPostings = jobPostingRepository.findAll(pageable);
        return jobPostings.map(jobPostingMapper::mapJobPostingToDTO);
    }
    @Override
    public JobPostingDTO createJobPosting(JobPostingDTO jobPostingDTO) {
        try {
            JobPosting jobPosting = jobPostingMapper.mapDTOToJobPosting(jobPostingDTO);
            jobPosting.setPostDate(LocalDate.now());

            JobPosting savedJobPosting = jobPostingRepository.save(jobPosting);

            return jobPostingMapper.mapJobPostingToDTO(savedJobPosting);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error creating job posting. Illegal argument.", e);
        }
    }


    @Override
    public JobPostingDTO getJobPostingById(Long jobPostingId) {
        Optional<JobPosting> jobPostingOptional = jobPostingRepository.findById(jobPostingId);
        if (jobPostingOptional.isPresent()) {
            return jobPostingMapper.mapJobPostingToDTO(jobPostingOptional.get());
        } else {
            throw new RuntimeException("Job Posting not found with ID: " + jobPostingId);
        }
    }

    @Override
    public void updateJobPosting(Long jobPostingId, JobPostingDTO jobPostingDTO) {
        Optional<JobPosting> jobPostingOptional = jobPostingRepository.findById(jobPostingId);
        if (jobPostingOptional.isPresent()) {
            JobPosting existingJobPosting = jobPostingOptional.get();
            jobPostingMapper.updateJobPostingFromDTO(jobPostingDTO, existingJobPosting);
            jobPostingRepository.save(existingJobPosting);
        } else {
            throw new RuntimeException("Job Posting not found with ID: " + jobPostingId);
        }
    }

    @Override
    public void deleteJobPosting(Long jobPostingId) {
        Optional<JobPosting> jobPostingOptional = jobPostingRepository.findById(jobPostingId);
        jobPostingOptional.ifPresent(jobPostingRepository::delete);
    }
    @Transactional
    @Override
    public JobPostingDTO createJobPostingForEmployeer(UUID employeerId, JobPostingDTO jobPostingDTO) {
        try {
            Employeer employeer = employeerRepository.findById(employeerId)
                    .orElseThrow(() -> new EntityNotFoundException("Employeer not found with ID: " + employeerId));

            JobPosting jobPosting = jobPostingMapper.mapDTOToJobPosting(jobPostingDTO);
            jobPosting.setPostDate(LocalDate.now());
            jobPosting.setEmployeer(employeer);

            JobPosting savedJobPosting = jobPostingRepository.save(jobPosting);

            return jobPostingMapper.mapJobPostingToDTO(savedJobPosting);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error creating job posting. Illegal argument.", e);
        }
    }

    @Override
    public List<JobPostingDTO> getAllJobPostingsByEmployerId(UUID employerId) {
        Employeer employer = employeerRepository.findById(employerId)
                .orElseThrow(() -> new EntityNotFoundException("Employeer not found with ID: " + employerId));

        List<JobPosting> jobPostings = jobPostingRepository.findByEmployeer(employer);
        return jobPostings.stream()
                .map(jobPostingMapper::mapJobPostingToDTO)
                .collect(Collectors.toList());
    }

}
