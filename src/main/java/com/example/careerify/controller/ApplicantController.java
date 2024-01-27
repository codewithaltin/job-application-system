package com.example.careerify.controller;

import com.example.careerify.common.dto.ApplicantDTO;
import com.example.careerify.service.ApplicantService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/applicants")
public class ApplicantController {

    private final ApplicantService applicantService;
    @Autowired
    public ApplicantController(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    @PostMapping
    public ResponseEntity<Object> createApplicant(@RequestBody ApplicantDTO applicantDTO) {
        try {
            ApplicantDTO createdApplicant = applicantService.createApplicant(applicantDTO);
            return new ResponseEntity<>(createdApplicant, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating applicant: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getApplicantById(@PathVariable UUID id) {
        ApplicantDTO applicantDTO = applicantService.getApplicantById(id);
        return new ResponseEntity<>(applicantDTO, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Page<ApplicantDTO>> getAllApplicants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest pageable = PageRequest.of(page, size);
        Page<ApplicantDTO> applicants = applicantService.getAllApplicants(pageable);
        return new ResponseEntity<>(applicants, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateApplicant(@PathVariable UUID id, @RequestBody ApplicantDTO updateDTO) {
        applicantService.updateApplicant(id, updateDTO);
        return new ResponseEntity<>("Applicant updated successfully", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteApplicant(@PathVariable UUID id) {
        applicantService.deleteApplicant(id);
        return new ResponseEntity<>("Applicant deleted successfully", HttpStatus.NO_CONTENT);
    }

}
