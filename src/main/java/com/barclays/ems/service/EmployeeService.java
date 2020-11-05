package com.barclays.ems.service;

import com.barclays.ems.model.Employee;

import java.util.HashSet;

public interface EmployeeService {
    boolean addEmployee(Employee employee);

    Employee getEmployee(int id);

    boolean updateEmployee(int id, String name, String designation, String department);

    HashSet<Employee> allEmployees();

    boolean deleteEmployee(int id);
}
