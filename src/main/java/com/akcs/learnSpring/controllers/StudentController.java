package com.akcs.learnSpring.controllers;

import com.akcs.learnSpring.communication.APIResponse;
import com.akcs.learnSpring.communication.StudentRequest;
import com.akcs.learnSpring.dto.StudentDTO;
import com.akcs.learnSpring.enums.StudentGrade;
import com.akcs.learnSpring.exceptions.StudentAlreadyExists;
import com.akcs.learnSpring.exceptions.StudentNotFoundException;
import com.akcs.learnSpring.services.IStudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/students")
public class StudentController {

    private final IStudentService studentService;

    @PostMapping
    public ResponseEntity<APIResponse> postStudent(
            @Valid  @RequestBody StudentRequest request
    ) {
        try {
           StudentDTO student = studentService.postStudent(request);
           return ResponseEntity.status(HttpStatus.CREATED)
                   .body(new APIResponse("success", student, Instant.now()));
        } catch (StudentAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new APIResponse(e.getMessage(), null, Instant.now()));
        }
    }

    @GetMapping
    public ResponseEntity<APIResponse> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new APIResponse("success", students, Instant.now()));
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<APIResponse> getStudentById(@PathVariable Long id) {
        try {
            StudentDTO student = studentService.getStudentById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new APIResponse("success", student, Instant.now()));
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new APIResponse(e.getMessage(), null, Instant.now()));
        }
    }

    @GetMapping("/student/grade/{grade}")
    public ResponseEntity<APIResponse> getStudentsByGrade(@PathVariable StudentGrade grade) {
        List<StudentDTO> students = studentService.getStudentsByGrade(grade);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new APIResponse("success", students, Instant.now()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> putStudent(@PathVariable Long id, @Valid @RequestBody StudentRequest request) {
        try {
            StudentDTO student = studentService.putStudent(id, request);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new APIResponse("success", student, Instant.now()));
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new APIResponse(e.getMessage(), null, Instant.now()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new APIResponse("success", null, Instant.now()));
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new APIResponse(e.getMessage(), null, Instant.now()));
        }
    }
}
