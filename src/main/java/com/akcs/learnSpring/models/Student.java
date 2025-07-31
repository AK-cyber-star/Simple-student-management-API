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
    private String password;

    @Enumerated(EnumType.STRING)
    private StudentGrade grade;

    public Student(String name, String email, String password, StudentGrade grade) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.grade = grade;
    }
}
