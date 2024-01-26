package com.example.careerify.repository;

import com.example.careerify.model.ApplicationManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplicationManagerRepository extends JpaRepository<ApplicationManager, UUID> {
}
