package com.example.careerify.common.mappers;

import com.example.careerify.common.dto.ApplicationRequestDTO;
import com.example.careerify.common.dto.ApplicationResponseDTO;
import com.example.careerify.model.Application;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Mappings({
            @Mapping(target = "jobListing.id", source = "jobListingId"),
            @Mapping(target = "applicant.id", source = "applicantId")
    })
    public Application mapRequestDTOToApplication(ApplicationRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, Application.class);
    }

    @Mappings({
            @Mapping(target = "jobListingId", source = "jobListing.id"),
            @Mapping(target = "applicantId", source = "applicant.id")
    })
    public ApplicationResponseDTO mapApplicationToResponseDTO(Application application) {
        return modelMapper.map(application, ApplicationResponseDTO.class);
    }

    @InheritConfiguration
    public Application mapRequestDTOToApplication(ApplicationRequestDTO requestDTO, Application existingApplication) {
        modelMapper.map(requestDTO, existingApplication);
        return existingApplication;
    }

    public List<ApplicationResponseDTO> mapApplicationsToResponseDTOs(List<Application> applications) {
        return applications.stream()
                .map(this::mapApplicationToResponseDTO)
                .toList();
    }
}
