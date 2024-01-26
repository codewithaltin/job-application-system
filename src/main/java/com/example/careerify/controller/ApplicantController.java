package com.example.careerify.controller;

import com.example.careerify.common.dto.ApplicantDTO;
import com.example.careerify.service.ApplicantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applicants")
public class ApplicantController {

    private final ApplicantService applicantService;

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
}
