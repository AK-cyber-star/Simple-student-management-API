package com.akcs.learnSpring.repositories;

import com.akcs.learnSpring.enums.StudentGrade;
import com.akcs.learnSpring.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByOrderByIdAsc();
    Optional<Student> findByEmail(String email);
    List<Student> findAllByGrade(StudentGrade grade);
}
