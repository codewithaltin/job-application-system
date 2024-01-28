package com.example.careerify.controller;

import com.example.careerify.common.dto.JobPostingDTO;
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
    public ResponseEntity<Page<JobPostingDTO>> getAllJobPostings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<JobPostingDTO> jobPostings = jobPostingService.getAllJobPostings(pageable);
        return new ResponseEntity<>(jobPostings, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JobPostingDTO> createJobPosting(@RequestBody JobPostingDTO jobPostingDTO) {
        JobPostingDTO createdJobPosting = jobPostingService.createJobPosting(jobPostingDTO);
        return new ResponseEntity<>(createdJobPosting, HttpStatus.CREATED);
    }
    @PostMapping("/create/{employerId}")
    public ResponseEntity<JobPostingDTO> createJobPostingForEmployer(
            @PathVariable UUID employerId,
            @Valid @RequestBody JobPostingDTO jobPostingDTO) {
        try {
            JobPostingDTO createdJobPosting = jobPostingService.createJobPostingForEmployeer(employerId, jobPostingDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdJobPosting);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobPostingDTO> getJobPostingById(@PathVariable Long id) {
        JobPostingDTO jobPostingDTO = jobPostingService.getJobPostingById(id);
        return new ResponseEntity<>(jobPostingDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateJobPosting(@PathVariable Long id, @RequestBody JobPostingDTO updateDTO) {
        jobPostingService.updateJobPosting(id, updateDTO);
        return new ResponseEntity<>("Job Posting updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteJobPosting(@PathVariable Long id) {
        jobPostingService.deleteJobPosting(id);
        return new ResponseEntity<>("Job Posting deleted successfully", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/employersJobPositions/{employerId}")
    public ResponseEntity<List<JobPostingDTO>> getAllJobPostingsByEmployerId(@PathVariable UUID employerId) {
        List<JobPostingDTO> jobPostings = jobPostingService.getAllJobPostingsByEmployerId(employerId);
        return ResponseEntity.ok(jobPostings);
    }
    @GetMapping("/bytitle")
    public ResponseEntity<List<JobPostingDTO>> getJobPostingsByTitle(@RequestParam String keyword) {
        List<JobPostingDTO> jobPostings = jobPostingService.getJobPostingsByTitle(keyword);
        return ResponseEntity.ok(jobPostings);
    }

    @GetMapping("/bysalary")
    public ResponseEntity<List<JobPostingDTO>> getJobPostingsBySalaryGreaterThan(@RequestParam float salary) {
        List<JobPostingDTO> jobPostings = jobPostingService.getJobPostingsBySalaryGreaterThan(salary);
        return ResponseEntity.ok(jobPostings);
    }

    @GetMapping("/bypostdate")
    public ResponseEntity<List<JobPostingDTO>> getJobPostingsByPostDateAfter(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate postDate) {
        List<JobPostingDTO> jobPostings = jobPostingService.getJobPostingsByPostDateAfter(postDate);
        return ResponseEntity.ok(jobPostings);
    }


    @GetMapping("/bycompanyname")
    public ResponseEntity<List<JobPostingDTO>> getJobPostingsByCompanyName(@RequestParam String companyName) {
        List<JobPostingDTO> jobPostings = jobPostingService.getJobPostingsByCompanyName(companyName);
        return ResponseEntity.ok(jobPostings);
    }
}
