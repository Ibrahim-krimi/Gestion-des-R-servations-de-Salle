package com.example.gestiondesrservationsdesalle.Service;


import com.example.gestiondesrservationsdesalle.Entity.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee save(Employee employee);
    public Employee getEmployeeById(int id);
    public List<Employee> getAllEmployees();
    public Employee updateEmploye(int id,Employee employee);

}
