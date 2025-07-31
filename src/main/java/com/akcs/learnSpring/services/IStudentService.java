package com.akcs.learnSpring.services;

import com.akcs.learnSpring.communication.StudentRequest;
import com.akcs.learnSpring.dto.StudentDTO;
import com.akcs.learnSpring.enums.StudentGrade;

import java.util.List;

public interface IStudentService {
    StudentDTO postStudent(StudentRequest request);

    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(Long id);
    StudentDTO getStudentByEmail(String email);
    List<StudentDTO> getStudentsByGrade(StudentGrade grade);

    StudentDTO putStudent(Long id, StudentRequest request);
    void deleteStudent(Long id);
}
