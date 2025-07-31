package com.akcs.learnSpring.repositories;

import com.akcs.learnSpring.enums.StudentGrade;
import com.akcs.learnSpring.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    Optional<Student> findAllByGrade(StudentGrade grade);
}
