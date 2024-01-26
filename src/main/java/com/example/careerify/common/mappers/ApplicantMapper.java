package com.example.careerify.common.mappers;

import com.example.careerify.common.dto.ApplicantDTO;
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
    public ApplicantDTO mapApplicantToDTO(Applicant applicant) {
        return modelMapper.map(applicant, ApplicantDTO.class);
    }

    @InheritConfiguration
    public  Applicant mapDTOToApplicant(ApplicantDTO applicantDTO) {
        return modelMapper.map(applicantDTO, Applicant.class);
    }
}

