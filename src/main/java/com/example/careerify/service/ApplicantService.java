package com.example.careerify.service;

import com.example.careerify.common.dto.ApplicantRequestDTO;
import com.example.careerify.common.dto.ApplicantResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface ApplicantService {

    ApplicantResponseDTO createApplicant(ApplicantRequestDTO applicantRequestDTO);
    ApplicantResponseDTO getApplicantById(UUID id);
    Page<ApplicantResponseDTO> getAllApplicants(Pageable pageable);
    void updateApplicant(UUID id, ApplicantRequestDTO updateDTO);
    void deleteApplicant(UUID applicantId);

}
