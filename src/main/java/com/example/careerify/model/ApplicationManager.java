package com.example.careerify.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@Data

public class ApplicationManager extends User {

    public ApplicationManager(){ super(); }

}
