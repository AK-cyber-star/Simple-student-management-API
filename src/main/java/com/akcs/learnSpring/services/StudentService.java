package com.akcs.learnSpring.services;

import com.akcs.learnSpring.communication.StudentRequest;
import com.akcs.learnSpring.dto.StudentDTO;
import com.akcs.learnSpring.enums.StudentGrade;
import com.akcs.learnSpring.exceptions.EmailAlreadyExists;
import com.akcs.learnSpring.exceptions.StudentAlreadyExists;
import com.akcs.learnSpring.exceptions.StudentNotFoundException;
import com.akcs.learnSpring.models.Student;
import com.akcs.learnSpring.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentService implements IStudentService{

    private final StudentRepository studentRepository;

    @Override
    public StudentDTO postStudent(StudentRequest request) {
        boolean isUserPresent = studentRepository.findByEmail(request.getEmail())
                .isPresent();
        if (isUserPresent) {
            throw new StudentAlreadyExists("student already exist.");
        }

        Student student = convertToStudentFromRequest(request);
        student = studentRepository.save(student);
        return convertToDto(student);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAllByOrderByIdAsc()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("student not found"));
        return convertToDto(student);
    }

    @Override
    public StudentDTO getStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new StudentNotFoundException("student not found"));
        return convertToDto(student);
    }

    @Override
    public List<StudentDTO> getStudentsByGrade(StudentGrade grade) {
        List<StudentDTO> students = studentRepository.findAllByGrade(grade)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return students;
    }

    @Override
    public StudentDTO putStudent(Long id, StudentRequest request) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("student not found"));

        // validate the email
        if (!existingStudent.getEmail().equalsIgnoreCase(request.getEmail())) {
            boolean emailExists = studentRepository.findByEmail(request.getEmail())
                    .filter(s -> !s.getId().equals(existingStudent.getId()))
                    .isPresent();
            if (emailExists) {
                throw new EmailAlreadyExists("email already exist");
            }
        }

        updateStudentFromRequest(request, existingStudent);
        existingStudent.setId(id);
        studentRepository.save(existingStudent);

        return convertToDto(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        boolean studentExists = studentRepository.findById(id)
                .isPresent();
        if (!studentExists) {
            throw new StudentNotFoundException("student not found");
        }

        studentRepository.deleteById(id);
    }

    private StudentDTO convertToDto(Student student) {
        return new StudentDTO(
               student.getId(),
               student.getName(),
               student.getEmail(),
                student.getGrade()
        );
    }

    private Student convertToStudentFromRequest(StudentRequest request) {
        return new Student(
                request.getName(),
                request.getEmail(),
                request.getGrade()
        );
    }

    private void updateStudentFromRequest(StudentRequest request, Student student) {
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setGrade(request.getGrade());
    }
}
