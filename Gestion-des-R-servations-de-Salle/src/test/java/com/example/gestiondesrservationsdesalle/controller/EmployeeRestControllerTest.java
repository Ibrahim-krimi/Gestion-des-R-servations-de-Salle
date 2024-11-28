package com.example.gestiondesrservationsdesalle.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class EmployeeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllEmployees() throws Exception {
        mockMvc.perform(get("/api/employees")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getEmployeeById() throws Exception {
        mockMvc.perform(get("/api/employees/{id}",1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }
}