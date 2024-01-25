package com.example.careerify.model;


import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false)
    private UUID id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String password;

}
