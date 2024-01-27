package com.example.careerify.common.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SkillDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;
}
