package com.akcs.learnSpring.communication;

import com.akcs.learnSpring.enums.StudentGrade;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentRequest {
    @NotNull
    private String name;
    @NotNull @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private StudentGrade grade;
}
