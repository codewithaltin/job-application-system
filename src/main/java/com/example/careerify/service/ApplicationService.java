package com.example.careerify.service;

import com.example.careerify.common.dto.ApplicationRequestDTO;
import com.example.careerify.common.dto.ApplicationResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ApplicationService {

    ApplicationResponseDTO applyForAJobListing(UUID applicantId, Long jobListingId) ;
    ApplicationResponseDTO getApplicationById(Long applicationId);

    List<ApplicationResponseDTO> getAllApplications();

    Page<ApplicationResponseDTO> getAllApplicationsPaged(Pageable pageable);

    void updateApplication(Long applicationId, ApplicationRequestDTO applicationRequestDTO);

    void deleteApplication(Long applicationId);


}
