package com.example.gestiondesrservationsdesalle.Repository;


import com.example.gestiondesrservationsdesalle.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmail(String email);
}