package com.example.careerify.common.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Data
@Getter
@Setter
public class SkillDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;
}
