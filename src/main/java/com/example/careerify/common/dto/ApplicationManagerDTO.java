package com.example.careerify.common.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ApplicationManagerDTO {
    private String firstName;
    private String lastName;
    private String password;
    private Date dateOfBirth;

}
