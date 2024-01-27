package com.example.careerify.repository;

import com.example.careerify.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface ExperienceRepository extends JpaRepository<Experience, UUID>  {
}
