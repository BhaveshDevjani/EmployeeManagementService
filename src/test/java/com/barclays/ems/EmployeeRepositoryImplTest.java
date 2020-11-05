package com.barclays.ems;

import com.barclays.ems.config.Database;
import com.barclays.ems.model.Employee;
import com.barclays.ems.repository.EmployeeRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeRepositoryImplTest {

    @Mock
    private Database database;

    @Mock
    private Connection c;

    @Mock
    private PreparedStatement stmt;

    @Mock
    private ResultSet rs;

    @InjectMocks
    EmployeeRepositoryImpl repository;

    private Employee employee;

    @Before
    public void setUp() throws Exception {

        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
        when(database.getConnection()).thenReturn(c);

        employee = new Employee();
        employee.setId(1);
        employee.setName("Bhavesh Devjani");
        employee.setDesignation("Software Enginner");
        employee.setDepartment("USCB");

        when(rs.next()).thenReturn(true).thenReturn(false);
        when(stmt.executeQuery()).thenReturn(rs);
        when(stmt.executeUpdate()).thenReturn(1);
        when(rs.getInt(1)).thenReturn(1);
        when(rs.getString(2)).thenReturn(employee.getName());
        when(rs.getString(3)).thenReturn(employee.getDesignation());
        when(rs.getString(4)).thenReturn(employee.getDepartment());
    }

    @Test
    public void testGetEmployee() {
        Employee e = repository.getEmployee(1);
        assertEquals(employee, e);
    }

    @Test
    public void testInsertEmployee() {
        assertTrue(repository.insertEmployee(employee));
    }

    @Test
    public void testUpdateEmployee() {
        assertTrue(repository.updateEmployee(employee));
    }

    @Test
    public void testDeleteEmployee() {
        assertTrue(repository.deleteEmployee(1));
    }

    @Test
    public void testAllEmployees() {
//        System.out.println(repository.getAllEmployees().getClass().getName());
//        assertThat(repository.getAllEmployees(), instanceOf(HashSet.class));
        assertTrue(repository.getAllEmployees().contains(employee));
    }

}
