package com.example.careerify.common.mappers;

import com.example.careerify.common.dto.JobPostingDTO;
import com.example.careerify.model.JobPosting;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class JobPostingMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public JobPostingMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public JobPostingDTO mapJobPostingToDTO(JobPosting jobPosting) {
        return modelMapper.map(jobPosting, JobPostingDTO.class);
    }

    public JobPosting mapDTOToJobPosting(JobPostingDTO jobPostingDTO) {
        return modelMapper.map(jobPostingDTO, JobPosting.class);
    }
    public void updateJobPostingFromDTO(JobPostingDTO jobPostingDTO, JobPosting jobPosting) {
        modelMapper.map(jobPostingDTO, jobPosting);
    }


}
