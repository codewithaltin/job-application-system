package com.example.careerify.controller;

import com.example.careerify.common.dto.ApplicationRequestDTO;
import com.example.careerify.common.dto.ApplicationResponseDTO;
import com.example.careerify.service.ApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<ApplicationResponseDTO> applyForAJobListing(
            @RequestParam UUID applicantId,
            @RequestParam Long jobListingId) {
        ApplicationResponseDTO createdApplication = applicationService.applyForAJobListing(applicantId, jobListingId);
        return ResponseEntity.created(URI.create("/api/applications/" + createdApplication.getId())).body(createdApplication);
    }


    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationResponseDTO> getApplicationById(@PathVariable Long applicationId) {
        ApplicationResponseDTO application = applicationService.getApplicationById(applicationId);
        return ResponseEntity.ok(application);
    }

    @GetMapping
    public ResponseEntity<Page<ApplicationResponseDTO>> getAllApplications(Pageable pageable) {
        Page<ApplicationResponseDTO> applicationsPage = applicationService.getAllApplicationsPaged(pageable);
        return ResponseEntity.ok(applicationsPage);

    }
    @PutMapping("/{applicationId}")
    public ResponseEntity<Void> updateApplication(
            @PathVariable Long applicationId,
            @RequestBody ApplicationRequestDTO applicationRequestDTO) {
        applicationService.updateApplication(applicationId, applicationRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{applicationId}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long applicationId) {
        applicationService.deleteApplication(applicationId);
        return ResponseEntity.noContent().build();
    }

}
