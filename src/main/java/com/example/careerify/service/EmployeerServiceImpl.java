package com.example.careerify.service;

import com.example.careerify.common.dto.EmployeerDTO;
import com.example.careerify.common.mappers.EmployeerMapper;
import com.example.careerify.model.Employeer;
import com.example.careerify.repository.EmployeerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class EmployeerServiceImpl implements EmployeerService{


    private EmployeerRepository employeerRepository;
    private EmployeerMapper employeerMapper;
    public EmployeerServiceImpl(EmployeerRepository employeerRepository, EmployeerMapper employeerMapper) {
        this.employeerRepository = employeerRepository;
        this.employeerMapper = employeerMapper;
    }

    @Override
    public EmployeerDTO createEmployeer(EmployeerDTO employeerDTO) {
        try {
            Employeer employeer = employeerMapper.mapDTOToEmployeer(employeerDTO);

            Employeer savedEmployeer = employeerRepository.save(employeer);

            return employeerMapper.mapEmployeerToDTO(savedEmployeer);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error creating employeer. Duplicate entry.", e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error creating employeer. Illegal argument.", e);
        }
    }

    @Override
    public EmployeerDTO getEmployeerById(UUID id) {
        Employeer employeer = employeerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employeer not found with ID: " + id));
        return employeerMapper.mapEmployeerToDTO(employeer);
    }

    @Override
    public Page<EmployeerDTO> getAllEmployeers(Pageable pageable) {
        Page<Employeer> employeers = employeerRepository.findAll(pageable);
        return employeers.map(employeerMapper::mapEmployeerToDTO);
    }
    @Override
    public void deleteEmployeer(UUID employeerId) {
        Employeer existingEmployeer = employeerRepository.findById(employeerId)
                .orElseThrow(() -> new EntityNotFoundException("Employeer not found with ID: " + employeerId));

        employeerRepository.delete(existingEmployeer);
    }
    @Override
    @Transactional
    public void updateEmployeer(UUID id, EmployeerDTO updateDTO) throws EntityNotFoundException {
        Employeer existingEmployeer = employeerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employeer not found with ID: " + id));

        employeerMapper.updateEmployeerFromDTO(updateDTO, existingEmployeer);

        employeerRepository.save(existingEmployeer);
    }

}
