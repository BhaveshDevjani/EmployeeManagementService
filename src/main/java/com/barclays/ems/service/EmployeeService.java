package com.barclays.ems.service;

import com.barclays.ems.model.Employee;
import com.barclays.ems.repository.EmployeeDao;
import com.barclays.ems.repository.EmployeeDaoImpl;

import java.util.*;

public class EmployeeService {

    EmployeeDao employeeDao = new EmployeeDaoImpl();


    public boolean addEmployee(Employee employee) {

        return employeeDao.insertEmployee(employee);
    }

    public Employee getEmployee(int id) {

        return employeeDao.getEmployee(id);
    }

    public boolean updateEmployee(Employee employee) {

        return employeeDao.updateEmployee(employee);
    }

    public HashSet<Employee> allEmployees() {

        return employeeDao.getAllEmployees();
    }

    public boolean deleteEmployee(int id) {

        return employeeDao.deleteEmployee(id);
    }
}
