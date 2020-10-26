package com.barclays.ems.service;

import com.barclays.ems.model.Employee;
import com.barclays.ems.repository.EmployeeRepository;
import com.barclays.ems.repository.EmployeeRepositoryImpl;

import java.util.*;

public class EmployeeService {

    EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();


    public boolean addEmployee(Employee employee) {

        return employeeRepository.insertEmployee(employee);
    }

    public Employee getEmployee(int id) {

        return employeeRepository.getEmployee(id);
    }

    public boolean updateEmployee(Employee employee) {

        return employeeRepository.updateEmployee(employee);
    }

    public HashSet<Employee> allEmployees() {

        return employeeRepository.getAllEmployees();
    }

    public boolean deleteEmployee(int id) {

        return employeeRepository.deleteEmployee(id);
    }
}
