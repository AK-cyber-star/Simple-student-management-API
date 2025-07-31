package com.akcs.learnSpring.dto;

import com.akcs.learnSpring.enums.StudentGrade;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private StudentGrade grade;
}
