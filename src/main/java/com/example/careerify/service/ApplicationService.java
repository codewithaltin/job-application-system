package com.example.careerify.service;

import com.example.careerify.common.dto.ApplicationRequestDTO;
import com.example.careerify.common.dto.ApplicationResponseDTO;
import com.example.careerify.common.enums.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ApplicationService {

    ApplicationResponseDTO applyForAJobListing(UUID applicantId, Long jobListingId);

    ApplicationResponseDTO getApplicationById(Long applicationId);

    List<ApplicationResponseDTO> getAllApplications();

    Page<ApplicationResponseDTO> getAllApplicationsPaged(Pageable pageable);

    void updateApplication(Long applicationId, ApplicationRequestDTO applicationRequestDTO);

    void deleteApplication(Long applicationId);

    List<ApplicationResponseDTO> getApplicationsByStatusAndApplicant(ApplicationStatus status, UUID applicantId);

    List<ApplicationResponseDTO> getApplicationsByJobListing(Long jobListingId);


    List<ApplicationResponseDTO> getApplicationsByStatus(ApplicationStatus status);

    long countApplicationsByStatusAndJobListing(ApplicationStatus status, Long jobListingId);

    long countApplicationsByJobListingAndApplicant(Long jobListingId, UUID applicantId);

    List<ApplicationResponseDTO> getApplicationsByJobListingAndApplicant(Long jobListingId, UUID applicantId);

    void updateApplicationStatus(Long applicationId, ApplicationStatus newStatus);


}
