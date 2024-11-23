package com.example.gestiondesrservationsdesalle.service;


import com.example.gestiondesrservationsdesalle.Entity.Employee;
import com.example.gestiondesrservationsdesalle.Enum.RoleEmployee;
import com.example.gestiondesrservationsdesalle.Repository.EmployeeRepository;
import com.example.gestiondesrservationsdesalle.Service.EmployeeService;
import com.example.gestiondesrservationsdesalle.ServiceImpl.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmlpoyeeServiceImplTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    Employee employee;

    @BeforeEach
    public void setUp() {
        //Given
        employee = Employee.builder()
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
        Employee created = employeeService.save(employee);
        //then
        Assertions.assertEquals(employee.getRole(), created.getRole());
    }

    @Test
    public void WhenUpdateEmployeeItShouldReturnEmployeeUpdated() {
        //Given
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        //when
        Employee employeeSaved = employeeRepository.save(employee);
        employeeSaved.setNom("jojo");
        Employee updateEmployee = employeeService.updateEmploye(1,employeeSaved);
        //then
        Assertions.assertNotNull(updateEmployee);
        Assertions.assertEquals("jojo", employeeSaved.getNom());
        Assertions.assertEquals(employeeSaved.getId(), updateEmployee.getId());
    }



}
