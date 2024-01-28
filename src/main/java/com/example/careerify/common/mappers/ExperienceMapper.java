package com.example.careerify.common.mappers;

import com.example.careerify.common.dto.ExperienceDTO;
import com.example.careerify.model.Experience;
import org.mapstruct.InheritConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class ExperienceMapper {
    @Autowired
    private ModelMapper modelMapper ;

    @InheritConfiguration
    public ExperienceDTO mapExperienceToDTO(Experience experience) {
        return modelMapper.map(experience, ExperienceDTO.class);
    }

    @InheritConfiguration
    public  Experience mapDTOToExperience(ExperienceDTO experienceDTO) {
        return modelMapper.map(experienceDTO, Experience.class);
    }

}
