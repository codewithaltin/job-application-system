package com.example.careerify.service;

import com.example.careerify.common.dto.EmployeerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EmployeerService {

    EmployeerDTO createEmployeer(EmployeerDTO employeerDTO);
    EmployeerDTO getEmployeerById(UUID id);
    Page<EmployeerDTO> getAllEmployeers(Pageable pageable);
    void updateEmployeer(UUID id, EmployeerDTO updateDTO);
    void deleteEmployeer(UUID employeerId);


}
