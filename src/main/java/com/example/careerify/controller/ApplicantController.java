package com.example.careerify.controller;

import com.example.careerify.common.dto.ApplicantRequestDTO;
import com.example.careerify.common.dto.ApplicantResponseDTO;
import com.example.careerify.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/applicants")
public class ApplicantController {

    private final ApplicantService applicantService;
    @Autowired
    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }


    @PostMapping
    public ResponseEntity<Object> createApplicant(@RequestBody ApplicantRequestDTO applicantRequestDTO) {
        try {
            ApplicantResponseDTO createdApplicant = applicantService.createApplicant(applicantRequestDTO);
            return new ResponseEntity<>(createdApplicant, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating applicant: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getApplicantById(@PathVariable UUID id) {
        ApplicantResponseDTO applicantRequestDTO = applicantService.getApplicantById(id);
        return new ResponseEntity<>(applicantRequestDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<ApplicantResponseDTO>> getAllApplicants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest pageable = PageRequest.of(page, size);
        Page<ApplicantResponseDTO> applicants = applicantService.getAllApplicants(pageable);
        return new ResponseEntity<>(applicants, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateApplicant(@PathVariable UUID id, @RequestBody ApplicantRequestDTO updateDTO) {
        applicantService.updateApplicant(id, updateDTO);
        return new ResponseEntity<>("Applicant updated successfully", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteApplicant(@PathVariable UUID id) {
        applicantService.deleteApplicant(id);
        return new ResponseEntity<>("Applicant deleted successfully", HttpStatus.NO_CONTENT);
    }

}
