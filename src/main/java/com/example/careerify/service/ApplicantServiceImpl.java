package com.example.careerify.service;

import com.example.careerify.common.dto.ApplicantRequestDTO;
import com.example.careerify.common.dto.ApplicantResponseDTO;
import com.example.careerify.common.mappers.ApplicantMapper;
import com.example.careerify.model.Applicant;
import com.example.careerify.repository.ApplicantRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper applicantMapper;
    public ApplicantServiceImpl(ApplicantRepository applicantRepository,ApplicantMapper applicantMapper) {
        this.applicantRepository = applicantRepository;
        this.applicantMapper = applicantMapper;
    }

    @Override
    public ApplicantResponseDTO createApplicant(ApplicantRequestDTO applicantRequestDTO) {
        try {
            Applicant applicant = applicantMapper.mapDTOToApplicant(applicantRequestDTO);

            Applicant savedApplicant = applicantRepository.save(applicant);

            return applicantMapper.mapApplicantToDTO(savedApplicant);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error creating applicant. Duplicate entry.", e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error creating applicant. Illegal argument.", e);
        }
    }


    @Override
    public ApplicantResponseDTO getApplicantById(UUID id) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Applicant not found with ID: " + id));
        return applicantMapper.mapApplicantToDTO(applicant);
    }

    @Override
    public Page<ApplicantResponseDTO> getAllApplicants(Pageable pageable) {
        Page<Applicant> applicants = applicantRepository.findAll(pageable);
        return applicants.map(applicantMapper::mapApplicantToDTO);
    }
    @Override
    public void deleteApplicant(UUID applicantId) {
        Applicant existingApplicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new EntityNotFoundException("Applicant not found with ID: " + applicantId));

        applicantRepository.delete(existingApplicant);
    }
    @Override
    @Transactional
    public void updateApplicant(UUID id, ApplicantRequestDTO updateDTO) throws EntityNotFoundException {
        Applicant existingApplicant = applicantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Applicant not found with ID: " + id));

        applicantMapper.updateApplicantFromDTO(updateDTO, existingApplicant);

        applicantRepository.save(existingApplicant);
    }

}
