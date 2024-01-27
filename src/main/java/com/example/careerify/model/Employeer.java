package com.example.careerify.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@Data
public class Employeer extends User{

    @NonNull
    private String companyName;

    public Employeer(){ super(); }

}
