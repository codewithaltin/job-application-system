package com.example.careerify.common.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ApplicantDTO {
    private String firstName;
    private String lastName;
    private String password;
    private Date dateOfBirth;
    private boolean status;
}
