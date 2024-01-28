package com.example.careerify.common.mappers;

import com.example.careerify.common.dto.ApplicantRequestDTO;
import com.example.careerify.common.dto.ApplicantResponseDTO;
import com.example.careerify.model.Applicant;
//import org.modelmapper.ModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.mapstruct.InheritConfiguration;
@Component
public class ApplicantMapper {

    @Autowired
    private ModelMapper modelMapper ;

    @InheritConfiguration
    public ApplicantResponseDTO mapApplicantToDTO(Applicant applicant) {
        return modelMapper.map(applicant, ApplicantResponseDTO.class);
    }

    @InheritConfiguration
    public  Applicant mapDTOToApplicant(ApplicantRequestDTO applicantRequestDTO) {
        return modelMapper.map(applicantRequestDTO, Applicant.class);
    }

    @InheritConfiguration
    public void updateApplicantFromDTO(ApplicantRequestDTO updateDTO, Applicant existingApplicant) {
        modelMapper.map(updateDTO, existingApplicant);
    }

}

