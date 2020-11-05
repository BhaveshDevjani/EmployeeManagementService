package com.barclays.ems.service;

import com.barclays.ems.model.Employee;
import com.barclays.ems.repository.EmployeeRepository;
import com.barclays.ems.repository.EmployeeRepositoryImpl;
import com.barclays.ems.utils.Constants;
import com.barclays.ems.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public boolean updateEmployee(int id, String name, String designation, String department) {

        Employee currentEmployee = getEmployee(id);
        if ( currentEmployee == null) {
            return false;
        }

        Employee employee = new Employee();
        employee.setId(id);

        employee.setName(name==null ? currentEmployee.getName() : name);
        employee.setDesignation(designation==null ? currentEmployee.getDesignation() : designation);
        employee.setDepartment(department==null ? currentEmployee.getDepartment() : department);

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
