package com.example.gestiondesrservationsdesalle.ServiceImpl;

import com.example.gestiondesrservationsdesalle.Entity.Employee;
import com.example.gestiondesrservationsdesalle.Repository.EmployeeRepository;
import com.example.gestiondesrservationsdesalle.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public Employee save(Employee employee) {
        try {
            return employeeRepository.save(employee);
        }catch (Exception e){
        throw new RuntimeException(e);
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        return employee;

    }

    @Override
    public List<Employee> getAllEmployees() {
        try {
           List <Employee> employees = employeeRepository.findAll();
            return employees;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee updateEmploye(int id, Employee employeeUpdate) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with id " + id + " not found"));

        employee.setNom(employeeUpdate.getNom());
        employee.setEmail(employeeUpdate.getEmail());
        employee.setRole(employeeUpdate.getRole());

        return employeeRepository.save(employee);
    }

}
