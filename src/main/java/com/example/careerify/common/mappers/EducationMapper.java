package com.example.careerify.common.mappers;

import com.example.careerify.model.Education;
import org.mapstruct.InheritConfiguration;
import com.example.careerify.common.dto.EducationDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EducationMapper {

    @Autowired
    private ModelMapper modelMapper ;

    @InheritConfiguration
    public EducationDTO mapEducationToDTO(Education education) {
        return modelMapper.map(education, EducationDTO.class);
    }

    @InheritConfiguration
    public Education mapDTOToEducation(EducationDTO educationDTO) {
        return modelMapper.map(educationDTO, Education.class);
    }

    @InheritConfiguration
    public void updateEducationFromDTO(EducationDTO updateDTO, Education education) {
        modelMapper.map(updateDTO, education);
    }

}
