package com.example.careerify.service;

import com.example.careerify.common.dto.EmployeerDTO;
import com.example.careerify.common.mappers.EmployeerMapper;

public class EmployeerServiceImplementation implements EmployeerService{


    private EmployeerServiceImplementation employeerService;
    private EmployeerMapper employeerMapper;

    public EmployeerServiceImplementation(EmployeerServiceImplementation employeerService, EmployeerMapper employeerMapper){
        this.employeerService = employeerService;
        this.employeerMapper = employeerMapper;
    }

    @Override
    public EmployeerDTO createEmployer(EmployeerDTO employeerDTO) {
        return null;
    }
}
