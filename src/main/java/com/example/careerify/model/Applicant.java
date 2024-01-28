package com.example.careerify.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Applicant extends User {

    @NonNull
    private boolean status;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications;

}
