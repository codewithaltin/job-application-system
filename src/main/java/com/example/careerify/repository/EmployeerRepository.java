package com.example.careerify.repository;

import com.example.careerify.model.Employeer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EmployeerRepository extends JpaRepository<Employeer, UUID> {
}
