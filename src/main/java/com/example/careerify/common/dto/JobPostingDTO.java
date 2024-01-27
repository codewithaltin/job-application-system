package com.example.careerify.common.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class JobPostingDTO {

    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @Positive(message = "Salary must be a positive value")
    private float salary;

    @NotNull(message = "Employer ID cannot be null")
    private UUID employeerId;

    @NotNull(message = "Post date cannot be null")
    private LocalDate postDate;

    @NotNull(message = "End date cannot be null")
    private LocalDate endDate;

}
