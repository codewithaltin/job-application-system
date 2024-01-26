package com.example.careerify.service;

import com.example.careerify.common.dto.ApplicantDTO;
import com.example.careerify.common.mappers.ApplicantMapper;
import com.example.careerify.model.Applicant;
import com.example.careerify.repository.ApplicantRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final ApplicantMapper applicantMapper;
    public ApplicantServiceImpl(ApplicantRepository applicantRepository,ApplicantMapper applicantMapper) {
        this.applicantRepository = applicantRepository;
        this.applicantMapper = applicantMapper;
    }
    @Override
    public ApplicantDTO createApplicant(ApplicantDTO applicantDTO) {
        try {
            Applicant applicant = applicantMapper.mapDTOToApplicant(applicantDTO);

            Applicant savedApplicant = applicantRepository.save(applicant);

            return applicantMapper.mapApplicantToDTO(savedApplicant);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error creating applicant. Duplicate entry.", e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error creating applicant. Illegal argument.", e);
        }
    }


}
