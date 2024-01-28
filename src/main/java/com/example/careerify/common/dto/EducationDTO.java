package com.example.careerify.common.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Data
@Getter
@Setter
public class EducationDTO {
    private String fieldOfStudy;

    private String school;

    private Date endDate;

    private Date startDate;

    private String location;

    private String grade;
}
