package com.barclays.ems.repository;

import com.barclays.ems.config.Database;
import com.barclays.ems.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashSet;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Autowired
    Database database;

    private Employee extractUserFromResultSet(ResultSet rs) {

        Employee employee = new Employee();

        try {

            employee.setId(rs.getInt(1));
            employee.setName(rs.getString(2));
            employee.setDesignation(rs.getString(3));
            employee.setDepartment(rs.getString(4));

        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }

        return employee;
    }

    @Override
    public boolean insertEmployee(Employee employee) {

        Connection con = database.getConnection();

        try {

            PreparedStatement stmt = con.prepareStatement("insert into employee values(?,?,?,?)");

            stmt.setInt(1, employee.getId());
            stmt.setString(2, employee.getName());
            stmt.setString(3, employee.getDesignation());
            stmt.setString(4, employee.getDepartment());

            int i = stmt.executeUpdate();

            if (i==1) {
                return true;
            }
            con.close();

        } catch (SQLException throwables) {
            System.err.println(throwables.getMessage());
        }

        return false;
    }

    @Override
    public Employee getEmployee(int id) {

        Connection con = database.getConnection();

            try {

                PreparedStatement stmt = con.prepareStatement("select * from employee where id=(?)");
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();


                if (rs.next()) {
                    return extractUserFromResultSet(rs);
                }
                con.close();


            } catch (SQLException throwables) {
                System.err.println(throwables.getMessage());
            }

            return null;
    }

    @Override
    public boolean updateEmployee(Employee employee) {

        Connection con = database.getConnection();

        try {

            PreparedStatement stmt = con.prepareStatement("update employee set name=(?), designation=(?)," +
                    " department=(?) where id=(?)");

            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getDesignation());
            stmt.setString(3, employee.getDepartment());
            stmt.setInt(4, employee.getId());

            int i = stmt.executeUpdate();

            if (i==1) {
                return true;
            }
            con.close();

        } catch (SQLException throwables) {
            System.err.println(throwables.getMessage());
        }

        return false;
    }

    @Override
    public HashSet<Employee> getAllEmployees() {

        Connection con = database.getConnection();

        try {

            PreparedStatement stmt = con.prepareStatement("select * from employee");
            ResultSet rs = stmt.executeQuery();

            HashSet<Employee> employees = new HashSet<>();
            while (rs.next()) {

                employees.add(extractUserFromResultSet(rs));
            }
            con.close();

            return employees;

        } catch (SQLException throwables) {
            System.err.println(throwables.getMessage());
        }

        return null;

    }

    @Override
    public boolean deleteEmployee(int id) {

        Connection con = database.getConnection();

        try {

            PreparedStatement stmt = con.prepareStatement("delete from employee where id=(?)");

            stmt.setInt(1, id);

            int i = stmt.executeUpdate();

            if (i==1) {
                return true;
            }
            con.close();

        } catch (SQLException throwables) {
            System.err.println(throwables.getMessage());
        }

        return false;

    }
}
