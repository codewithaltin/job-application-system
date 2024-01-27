package com.example.careerify.repository;

import com.example.careerify.model.Employeer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeerRepository extends JpaRepository<Employeer, UUID> {
}
