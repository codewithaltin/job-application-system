package com.example.careerify.controller;

import com.example.careerify.common.dto.JobPostingResponseDTO;
import com.example.careerify.service.JobPostingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/job-postings")
public class JobPostingController {

    private final JobPostingService jobPostingService;

    public JobPostingController(JobPostingService jobPostingService) {
        this.jobPostingService = jobPostingService;
    }

    @GetMapping
    public ResponseEntity<Page<JobPostingResponseDTO>> getAllJobPostings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<JobPostingResponseDTO> jobPostings = jobPostingService.getAllJobPostings(pageable);
        return new ResponseEntity<>(jobPostings, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JobPostingResponseDTO> createJobPosting(@RequestBody JobPostingResponseDTO jobPostingResponseDTO) {
        JobPostingResponseDTO createdJobPosting = jobPostingService.createJobPosting(jobPostingResponseDTO);
        return new ResponseEntity<>(createdJobPosting, HttpStatus.CREATED);
    }
    @PostMapping("/create/{employerId}")
    public ResponseEntity<JobPostingResponseDTO> createJobPostingForEmployer(
            @PathVariable UUID employerId,
            @Valid @RequestBody JobPostingResponseDTO jobPostingResponseDTO) {
        try {
            JobPostingResponseDTO createdJobPosting = jobPostingService.createJobPostingForEmployeer(employerId, jobPostingResponseDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdJobPosting);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobPostingResponseDTO> getJobPostingById(@PathVariable Long id) {
        JobPostingResponseDTO jobPostingResponseDTO = jobPostingService.getJobPostingById(id);
        return new ResponseEntity<>(jobPostingResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateJobPosting(@PathVariable Long id, @RequestBody JobPostingResponseDTO updateDTO) {
        jobPostingService.updateJobPosting(id, updateDTO);
        return new ResponseEntity<>("Job Posting updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteJobPosting(@PathVariable Long id) {
        jobPostingService.deleteJobPosting(id);
        return new ResponseEntity<>("Job Posting deleted successfully", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employersJobPositions/{employerId}")
    public ResponseEntity<List<JobPostingResponseDTO>> getAllJobPostingsByEmployerId(@PathVariable UUID employerId) {
        List<JobPostingResponseDTO> jobPostings = jobPostingService.getAllJobPostingsByEmployerId(employerId);
        return ResponseEntity.ok(jobPostings);
    }
    @GetMapping("/bytitle")
    public ResponseEntity<List<JobPostingResponseDTO>> getJobPostingsByTitle(@RequestParam String keyword) {
        List<JobPostingResponseDTO> jobPostings = jobPostingService.getJobPostingsByTitle(keyword);
        return ResponseEntity.ok(jobPostings);
    }

    @GetMapping("/bysalary")
    public ResponseEntity<List<JobPostingResponseDTO>> getJobPostingsBySalaryGreaterThan(@RequestParam float salary) {
        List<JobPostingResponseDTO> jobPostings = jobPostingService.getJobPostingsBySalaryGreaterThan(salary);
        return ResponseEntity.ok(jobPostings);
    }

    @GetMapping("/bypostdate")
    public ResponseEntity<List<JobPostingResponseDTO>> getJobPostingsByPostDateAfter(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate postDate) {
        List<JobPostingResponseDTO> jobPostings = jobPostingService.getJobPostingsByPostDateAfter(postDate);
        return ResponseEntity.ok(jobPostings);
    }


    @GetMapping("/bycompanyname")
    public ResponseEntity<List<JobPostingResponseDTO>> getJobPostingsByCompanyName(@RequestParam String companyName) {
        List<JobPostingResponseDTO> jobPostings = jobPostingService.getJobPostingsByCompanyName(companyName);
        return ResponseEntity.ok(jobPostings);
    }
}
