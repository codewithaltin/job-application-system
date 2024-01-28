package com.example.careerify.repository;
import com.example.careerify.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface SkillRepository extends JpaRepository<Skill, UUID>   {
}
