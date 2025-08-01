package com.akcs.learnSpring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired MockMvc mockMvc;

    @Test
    public void createStudentReturn201() throws Exception {
        mockMvc.perform(post("/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"Alex\", \"email\": \"alex@gmail.com\", \"grade\": \"A\" }")
        ).andExpect(status().isCreated());
    }

    @Test
    public void getStudentByIdTest() throws Exception {
        mockMvc.perform(get("/api/v1/students/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void getStudentsByGradeTest() throws Exception {
        mockMvc.perform(get("/api/v1/students/grade/{grade}", "C"))
                .andExpect(status().isOk());
    }

    @Test
    public void putStudentTest() throws Exception {
            mockMvc.perform(put("/api/v1/students/{id}", 2)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ \"name\": \"Alex Keros\", \"email\": \"alex32@gmail.com\", \"grade\": \"A\"}")
            ).andExpect(status().isOk());
    }

    @Test
    public void deleteStudentTest() throws Exception {
        mockMvc.perform(delete("/api/v1/students/{id}", 1))
                .andExpect(status().isNoContent());
    }
}
