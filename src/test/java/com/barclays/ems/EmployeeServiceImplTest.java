package com.barclays.ems;

import com.barclays.ems.model.Employee;
import com.barclays.ems.repository.EmployeeRepository;
import com.barclays.ems.repository.EmployeeRepositoryImpl;
import com.barclays.ems.service.EmployeeServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

    static Employee employee = new Employee();
    static HashSet<Employee> employees = new HashSet<>();

    @Mock
    EmployeeRepository repository;

    @InjectMocks
    EmployeeServiceImpl service;

    @BeforeClass
    public static void onlyOnce() {

        employee.setId(1);
        employee.setName("Bhavesh Devjani");
        employee.setDepartment("USCB");
        employee.setDesignation("Software Engineer");

        employees.add(employee);
    }

    @Test
    public void testAddEmployee() {

        when(repository.insertEmployee(employee)).thenReturn(true);
        assertTrue(service.addEmployee(employee));
    }

    @Test
    public void testGetEmployee() {

        when(repository.getEmployee(1)).thenReturn(employee);
        assertEquals(employee, service.getEmployee(1));
    }

    @Test
    public void testUpdateEmployee() {

        when(repository.updateEmployee(employee)).thenReturn(true);
        when(repository.getEmployee(1)).thenReturn(employee);
        assertTrue(service.updateEmployee(employee.getId(), employee.getName(), employee.getDesignation(),
                employee.getDepartment()));
    }

    @Test
    public void testGetAllEmployees() {

        when(repository.getAllEmployees()).thenReturn(employees);
        assertTrue(service.allEmployees().contains(employee));
    }

    @Test
    public void testDeleteAllEmployees() {

        when(repository.deleteEmployee(1)).thenReturn(true);
        assertTrue(service.deleteEmployee(1));
    }


}
