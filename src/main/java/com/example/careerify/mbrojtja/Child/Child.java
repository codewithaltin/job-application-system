package com.example.careerify.mbrojtja.Child;

import com.example.careerify.mbrojtja.Parent.Parent;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;
}

