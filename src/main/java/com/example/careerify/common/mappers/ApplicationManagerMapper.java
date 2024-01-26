package com.example.careerify.common.mappers;

import com.example.careerify.common.dto.ApplicantDTO;
import com.example.careerify.common.dto.ApplicationManagerDTO;
import com.example.careerify.model.Applicant;
import com.example.careerify.model.ApplicationManager;
import org.mapstruct.InheritConfiguration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationManagerMapper {


    @Autowired
    private ModelMapper modelMapper ;

    @InheritConfiguration
    public ApplicationManagerDTO mapApplicationManagerToDTO(ApplicationManager applicationManager) {
        return modelMapper.map(applicationManager, ApplicationManagerDTO.class);
    }

    @InheritConfiguration
    public  ApplicationManager mapDTOToApplicationManager(ApplicationManagerDTO applicationManagerDTO) {
        return modelMapper.map(applicationManagerDTO, ApplicationManager.class);
    }
}
