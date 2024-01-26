package com.example.careerify.repository;

import com.example.careerify.model.Applicant;
import com.example.careerify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplicantRepository extends JpaRepository<Applicant, UUID> {
}
