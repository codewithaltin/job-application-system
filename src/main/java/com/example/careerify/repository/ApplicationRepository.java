package com.example.careerify.repository;

import com.example.careerify.common.enums.ApplicationStatus;
import com.example.careerify.model.Application;
import com.example.careerify.model.JobPosting;
import com.example.careerify.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStatusAndJobListingAndApplicant(ApplicationStatus status, JobPosting jobListing, Applicant applicant);
    List<Application> findByStatusAndApplicant(ApplicationStatus status, Applicant applicant);
    List<Application> findByJobListing(JobPosting jobListing);
    Application findTopByStatusAndJobListingAndApplicant(ApplicationStatus status, JobPosting jobListing, Applicant applicant);
    List<Application> findByStatus(ApplicationStatus status);
    long countByStatusAndJobListing(ApplicationStatus status, JobPosting jobListing);
    long countByJobListingAndApplicant(JobPosting jobListing, Applicant applicant);
    List<Application> findByJobListingAndApplicant(JobPosting jobListing, Applicant applicant);
}
