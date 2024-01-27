package com.example.careerify.service;

import com.example.careerify.common.dto.ApplicantDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface ApplicantService {
    ApplicantDTO getApplicantById(UUID id);

    Page<ApplicantDTO> getAllApplicants(Pageable pageable);
    void updateApplicant(UUID id, ApplicantDTO updateDTO);
    void deleteApplicant(UUID applicantId);
}
