package com.example.careerify.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Employeer extends User{

    @NonNull
    private String companyName;

    @OneToMany(mappedBy = "employeer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobPosting> jobPostings;

}
