package com.example.careerify.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    private String title;

    private String description;

    private float salary;

    @ManyToOne
    @JoinColumn(name = "employeer_id", nullable = false)
    private Employeer employeer;

    private LocalDate postDate;

    private LocalDate endDate;

}
