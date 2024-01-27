package com.example.careerify.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private UUID id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String password;

    @NonNull
    private LocalDate dateOfBirth;


}
