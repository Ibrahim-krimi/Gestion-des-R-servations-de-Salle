package com.example.gestiondesrservationsdesalle.service;


import com.example.gestiondesrservationsdesalle.Entity.Employee;
import com.example.gestiondesrservationsdesalle.Enum.RoleEmployee;
import com.example.gestiondesrservationsdesalle.Repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmlpoyeeServiceImplTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeService;

    Employee employee;

    @BeforeEach
    public void setUp() {
        //Given
        Employee employee = Employee.builder()
                .id(1)
                .nom("Ali")
                .email("ali@gmail.com")
                .role(RoleEmployee.MANAGER)
                .build();
    }

    @Test
    public void WhenSaveEmployeeItShouldReturnEmployee() {
        //Given


        //when
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        Employee created = employeeService.save(employee);
        //then
        Assertions.assertEquals(employee.getRole(), created.getRole());
    }

    @Test
    public void WhenUpdateEmployeeItShouldReturnEmployeeUpdated() {
        //Given
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        //when
        employee.setNom("jojo");
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        Employee updateEmployee = employeeService.updateEmploye(1,employee);
        //then

        Assertions.assertNotNull(updateEmployee);
        Assertions.assertEquals("Jojo", updateEmployee.getNom());
        Assertions.assertEquals(employee.getId(), updateEmployee.getId());
    }



}
