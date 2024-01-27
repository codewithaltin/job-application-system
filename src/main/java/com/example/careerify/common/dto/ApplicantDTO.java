package com.example.careerify.common.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Data
@Getter
@Setter
public class ApplicantDTO {
    private String firstName;
    private String lastName;
    private String password;
    private Date dateOfBirth;
    private boolean status;
}
