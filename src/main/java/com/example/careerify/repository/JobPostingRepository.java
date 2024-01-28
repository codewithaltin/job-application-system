package com.example.careerify.repository;

import com.example.careerify.model.Employeer;
import com.example.careerify.model.JobPosting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface JobPostingRepository  extends JpaRepository<JobPosting,Long> {
    List<JobPosting> findByEmployeer(Employeer employeer);
    List<JobPosting> findByTitleContaining(String keyword);
    List<JobPosting> findBySalaryGreaterThan(float salary);
    List<JobPosting> findByPostDateAfter(LocalDate postDate);
    List<JobPosting> findByEmployeerCompanyName(String companyName);

}
