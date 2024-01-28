package com.example.careerify.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;
@Getter
@Setter
public class JobPostingResponseDTO {
    private Long id;
    private String title;
    private String description;
    private float salary;
    private EmployeerDTO employer;
    private List<ApplicationResponseDTO> applications;
    private LocalDate postDate;
    private LocalDate endDate;

}
