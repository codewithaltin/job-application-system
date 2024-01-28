package com.example.careerify.controller;

import com.example.careerify.common.dto.ExperienceDTO;
import com.example.careerify.service.ExperienceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/experience")
public class ExperienceController {
    private final ExperienceService experienceService;
    
    @Autowired
    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @PostMapping
    public ResponseEntity<Object> createExperience(@RequestBody ExperienceDTO experienceDTO) {
        try {
            ExperienceDTO createdExperience = experienceService.createExperience(experienceDTO);
            return new ResponseEntity<>(createdExperience, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating experience: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create/{applicantId}")
    public ResponseEntity<ExperienceDTO> createExperienceForApplicant(
            @PathVariable UUID applicantId,
            @Valid @RequestBody ExperienceDTO experienceDTO) {
        try {
            ExperienceDTO experienceDTO1 = experienceService.createExperienceForApplicant(applicantId, experienceDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(experienceDTO1);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getExperienceById(@PathVariable UUID id) {
        ExperienceDTO experienceDTO = experienceService.getExperienceById(id);
        return new ResponseEntity<>(experienceDTO, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<Page<ExperienceDTO>> getAllExperience(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest pageable = PageRequest.of(page, size);
        Page<ExperienceDTO> experiences = experienceService.getAllExperiences(pageable);
        return new ResponseEntity<>(experiences, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteExperience(@PathVariable UUID id) {
        experienceService.deleteExperience(id);
        return new ResponseEntity<>("Experience deleted successfully", HttpStatus.NO_CONTENT);
    }
}
