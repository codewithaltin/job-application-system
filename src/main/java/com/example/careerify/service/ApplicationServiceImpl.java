package com.example.careerify.service;

import com.example.careerify.common.dto.ApplicationRequestDTO;
import com.example.careerify.common.dto.ApplicationResponseDTO;
import com.example.careerify.common.mappers.ApplicationMapper;
import com.example.careerify.model.Applicant;
import com.example.careerify.model.Application;
import com.example.careerify.model.JobPosting;
import com.example.careerify.repository.ApplicantRepository;
import com.example.careerify.repository.ApplicationRepository;
import com.example.careerify.repository.JobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicantRepository applicantRepository;
    private final JobPostingRepository jobPostingRepository;
    private final ApplicationMapper applicationMapper;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository,
                                  ApplicantRepository applicantRepository,
                                  JobPostingRepository jobPostingRepository,
                                  ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.applicantRepository = applicantRepository;
        this.jobPostingRepository = jobPostingRepository;
        this.applicationMapper = applicationMapper;
    }
    @Override
    public ApplicationResponseDTO applyForAJobListing(UUID applicantId, Long jobListingId) {
        Applicant applicant = getApplicantById(applicantId);
        JobPosting jobPosting = getJobPostingById(jobListingId);
        Application application = new Application();
        application.setApplicant(applicant);
        application.setJobListing(jobPosting);

        Application savedApplication = applicationRepository.save(application);
        return applicationMapper.mapApplicationToResponseDTO(savedApplication);
    }

    private Applicant getApplicantById(UUID applicantId) {
        return applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found with ID: " + applicantId));
    }
    private JobPosting getJobPostingById(Long jobPostingId) {
        return jobPostingRepository.findById(jobPostingId)
                .orElseThrow(() -> new RuntimeException("Job Posting not found with ID: " + jobPostingId));
    }
    @Override
    public ApplicationResponseDTO getApplicationById(Long applicationId) {
        Optional<Application> applicationOptional = applicationRepository.findById(applicationId);
        return applicationOptional.map(applicationMapper::mapApplicationToResponseDTO)
                .orElseThrow(() -> new RuntimeException("Application not found with ID: " + applicationId));
    }

    @Override
    public List<ApplicationResponseDTO> getAllApplications() {
        List<Application> applications = applicationRepository.findAll();
        return applicationMapper.mapApplicationsToResponseDTOs(applications);
    }

    @Override
    public Page<ApplicationResponseDTO> getAllApplicationsPaged(Pageable pageable) {
        Page<Application> applicationPage = applicationRepository.findAll(pageable);
        return applicationPage.map(applicationMapper::mapApplicationToResponseDTO);
    }
    @Override
    public void updateApplication(Long applicationId, ApplicationRequestDTO applicationRequestDTO) {
        Optional<Application> applicationOptional = applicationRepository.findById(applicationId);
        if (applicationOptional.isPresent()) {
            Application existingApplication = applicationOptional.get();
            applicationMapper.mapRequestDTOToApplication(applicationRequestDTO, existingApplication);
            applicationRepository.save(existingApplication);
        } else {
            throw new RuntimeException("Application not found with ID: " + applicationId);
        }
    }
    @Override
    public void deleteApplication(Long applicationId) {
        applicationRepository.deleteById(applicationId);
    }


}
