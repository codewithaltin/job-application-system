package com.example.careerify.common.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class EmployeerDTO {
    private String firstName;
    private String lastName;
    private String password;
    private Date dateOfBirth;
    private String companyName;
}
