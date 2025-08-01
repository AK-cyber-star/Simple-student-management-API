package com.akcs.learnSpring.models;

import com.akcs.learnSpring.enums.StudentGrade;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="students")
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private StudentGrade grade;

    public Student(String name, String email, StudentGrade grade) {
            this.name = name;
            this.email = email;
            this.grade = grade;
    }
}
