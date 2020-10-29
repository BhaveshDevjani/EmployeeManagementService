package com.barclays.ems.service;

import com.barclays.ems.model.Employee;
import com.barclays.ems.repository.EmployeeRepository;
import com.barclays.ems.repository.EmployeeRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public boolean addEmployee(Employee employee) {

        return employeeRepository.insertEmployee(employee);
    }

    @Override
    public Employee getEmployee(int id) {

        return employeeRepository.getEmployee(id);
    }

    @Override
    public boolean updateEmployee(Employee employee) {

        return employeeRepository.updateEmployee(employee);
    }

    @Override
    public HashSet<Employee> allEmployees() {

        return employeeRepository.getAllEmployees();
    }

    @Override
    public boolean deleteEmployee(int id) {

        return employeeRepository.deleteEmployee(id);
    }
}
