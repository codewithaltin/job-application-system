package com.example.careerify.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class ApplicantResponseDTO {
    private UUID id;
    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private List<ApplicationResponseDTO> applications;



}
