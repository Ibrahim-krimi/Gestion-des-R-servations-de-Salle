package com.example.gestiondesrservationsdesalle.integration;

import com.example.gestiondesrservationsdesalle.Entity.Employee;
import com.example.gestiondesrservationsdesalle.Enum.RoleEmployee;
import com.example.gestiondesrservationsdesalle.Repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

// Tester les Crud Operations sur le repo de Employee
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        //Given
        employee = Employee.builder()
                .nom("Ibrahim")
                .role(RoleEmployee.MANAGER)
                .email("ibrahimKrimi2@gmail.com").build();
    }

    @Test
    @DisplayName("Junit Repo test for save Emlpoyee ")
    public void thisTestShouldSaveEmployee() {

        //when
        Employee saveEmployee =this.employeeRepository.save(this.employee);
        //then
        Assertions.assertNotNull(saveEmployee);
        Assertions.assertNotEquals(0,saveEmployee.getId());
        }
    @Test
    @DisplayName("Junit Repo test for update Employee")
    public void thisTestShouldUpdateEmployee() {
        // when
        Employee saveEmployee =this.employeeRepository.save(this.employee);

        this.employee.setEmail("test@gmail.com");

       Employee saveEmployeeSecondTime =this.employeeRepository.save(this.employee);
        //then
        Assertions.assertEquals(this.employee.getEmail(),saveEmployeeSecondTime.getEmail());
    }

    @DisplayName("Junit Repo Test for Delete Employee")
    @Test
    public void thisTestShouldDeleteEmployee() {
        //when
        Employee saveEmployee =this.employeeRepository.save(this.employee);
        this.employeeRepository.delete(this.employee);
        Optional<Employee> employee = this.employeeRepository.findById(this.employee.getId());
        //then
        Assertions.assertTrue(employee.isEmpty());
    }

    @DisplayName("Junit Repo Test for Return Employee By Email")
    @Test
    public void thisTestShouldReturnEmployeeByEmail() {
        // when
        Employee saveEmployee =this.employeeRepository.save(this.employee);
        Employee employeeByEmail = this.employeeRepository.findByEmail("test@gmail.com");
        //
        Assertions.assertNotNull(employeeByEmail);
        Assertions.assertEquals(this.employee.getEmail(),employeeByEmail.getEmail());
    }

}
