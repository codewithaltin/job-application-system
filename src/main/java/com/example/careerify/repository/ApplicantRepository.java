package com.example.careerify.repository;

import com.example.careerify.model.Applicant;
import com.example.careerify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, UUID> {
}
