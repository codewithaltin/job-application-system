package com.example.careerify.service;
import com.example.careerify.common.dto.SkillDTO;
import com.example.careerify.common.mappers.SkillMapper;
import com.example.careerify.model.Applicant;
import com.example.careerify.model.Education;
import com.example.careerify.model.Experience;
import com.example.careerify.model.Skill;
import com.example.careerify.repository.ApplicantRepository;
import com.example.careerify.repository.SkillRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class SkillServiceImpl implements SkillService {
    private SkillRepository skillRepository;
    private SkillMapper skillMapper;
    private ApplicantRepository applicantRepository;

    public SkillServiceImpl(SkillRepository skillRepository, SkillMapper skillMapper){
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
    }

    @Override
    public SkillDTO createSkill(SkillDTO skillDTO) {
        try {
            Skill skill = skillMapper.mapDTOToSkill(skillDTO);

            Skill savedSkill = skillRepository.save(skill);

            return skillMapper.mapSkillToDTO(savedSkill);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error creating skill. Duplicate entry.", e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error creating skill. Illegal argument.", e);
        }
    }

    @Override
    public SkillDTO getSkillById(UUID id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found with ID: " + id));
        return skillMapper.mapSkillToDTO(skill);
    }

    @Override
    public Page<SkillDTO> getAllSkills(Pageable pageable) {
        Page<Skill> skills = skillRepository.findAll(pageable);
        return skills.map(skillMapper::mapSkillToDTO);
    }

    @Override
    public void deleteSkill(UUID skillId) {
        Skill existingSkill = skillRepository.findById(skillId)
                .orElseThrow(() -> new EntityNotFoundException("Skill not found with ID: " + skillId));

        skillRepository.delete(existingSkill);
    }

    @Override
    public SkillDTO createSkillForApplicant(UUID applicantId, SkillDTO skillDTO) {
        try {
            Applicant applicant = applicantRepository.findById(applicantId)
                    .orElseThrow(() -> new EntityNotFoundException("Applicant not found with ID: " + applicantId));

            Skill skill = skillMapper.mapDTOToSkill(skillDTO);
            skill.setApplicant(applicant);

            Skill skill1 = skillRepository.save(skill);

            return skillMapper.mapSkillToDTO(skill1);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error creating skill. Illegal argument.", e);
        }
    }
}
