package com.example.careerify.common.mappers;

import com.example.careerify.common.dto.ApplicationManagerDTO;
import com.example.careerify.common.dto.EmployeerDTO;
import com.example.careerify.model.ApplicationManager;
import com.example.careerify.model.Employeer;
import org.mapstruct.InheritConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class EmployeerMapper {

    @Autowired
    private ModelMapper modelMapper;

    @InheritConfiguration
    public EmployeerDTO mapEmployeerToDTO(Employeer employeer) {
        return modelMapper.map(employeer, EmployeerDTO.class);
    }

    @InheritConfiguration
    public Employeer mapDTOToEmployeer(EmployeerDTO employeerDTO) {
        return modelMapper.map(employeerDTO, Employeer.class);
    }


}
