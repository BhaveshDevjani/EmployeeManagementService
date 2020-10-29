package com.barclays.ems.controller;

import com.barclays.ems.model.Employee;
import com.barclays.ems.service.EmployeeService;
import com.barclays.ems.utils.Constants;
import com.barclays.ems.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
public class Controller {

    @Autowired
    EmployeeService service;


    @GetMapping("/employee/{id}")
    public ResponseEntity<Object> getEmployee(@PathVariable("id") int id) {

        Employee employee =  service.getEmployee(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(new Response(Constants.NOT_FOUND), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/employee")
    public HashSet<Employee> getUsers() {
        return service.allEmployees();
    }

    @PostMapping("/employee")
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) {

        boolean result = service.addEmployee(employee);

        if (result) {

            return new ResponseEntity<>(new Response(Constants.SUCCESS), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new Response(Constants.UNSUCCESSFUL), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping(value = "/employee/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable("id") int id,
                                 @RequestParam(value="name", required = false) String name,
                                 @RequestParam(value="designation", required = false)  String designation,
                                 @RequestParam(value="department", required = false) String department) {


        Employee currentEmployee = service.getEmployee(id);
        if ( currentEmployee == null) {
            return new ResponseEntity<>(new Response(Constants.NOT_FOUND), HttpStatus.BAD_REQUEST);
        }

        Employee employee = new Employee();
        employee.setId(id);

        employee.setName(name==null ? currentEmployee.getName() : name);
        employee.setDesignation(designation==null ? currentEmployee.getDesignation() : designation);
        employee.setDepartment(department==null ? currentEmployee.getDepartment() : department);

        boolean result = service.updateEmployee(employee);
        if (result) {

            return new ResponseEntity<>(new Response(Constants.UPDATED), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Response(Constants.NOT_FOUND), HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("id") int id) {
        boolean result = service.deleteEmployee(id);
        if (result) {

            return new ResponseEntity<>(new Response(Constants.SUCCESS), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new Response(Constants.NOT_FOUND), HttpStatus.BAD_REQUEST);
        }
    }


}
