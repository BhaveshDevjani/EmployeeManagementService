package com.barclays.ems.controller;

import com.barclays.ems.model.Employee;
import com.barclays.ems.service.EmployeeService;
import com.barclays.ems.utils.Constants;
import com.barclays.ems.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
public class Controller {

    @Autowired
    EmployeeService service;

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);


    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id,
                                                @RequestHeader("User-Agent") String requestName) {

        logger.info("GET/{id} request from User Agent: " + requestName);
        Employee employee =  service.getEmployee(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/employee")
    public HashSet<Employee> getUsers(@RequestHeader("User-Agent") String requestName) {

        logger.info("GET request from User Agent: " + requestName);
        return service.allEmployees();
    }

    @PostMapping("/employee")
    public ResponseEntity<Response> addEmployee(@RequestBody Employee employee,
                                                @RequestHeader("User-Agent") String requestName) {

        logger.info("POST request from User Agent: " + requestName);
        boolean result = service.addEmployee(employee);

        if (result) {

            return new ResponseEntity<>(new Response(Constants.SUCCESS), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new Response(Constants.UNSUCCESSFUL), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping(value = "/employee/{id}")
    public ResponseEntity<Response> updateEmployee(@PathVariable("id") int id,
                                 @RequestParam(value="name", required = false) String name,
                                 @RequestParam(value="designation", required = false)  String designation,
                                 @RequestParam(value="department", required = false) String department,
                                 @RequestHeader("User-Agent") String requestName) {


        logger.info("PUT request from User Agent: " + requestName);
        boolean result = service.updateEmployee(id, name, designation, department);
        if (result) {

            return new ResponseEntity<>(new Response(Constants.UPDATED), HttpStatus.OK);
        }

        return new ResponseEntity<>(new Response(Constants.NOT_FOUND), HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Response> deleteEmployee(@PathVariable("id") int id,
                                                   @RequestHeader("User-Agent") String requestName) {

        logger.info("DELETE request from User Agent: " + requestName);
        boolean result = service.deleteEmployee(id);
        if (result) {

            return new ResponseEntity<>(new Response(Constants.SUCCESS), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(new Response(Constants.NOT_FOUND), HttpStatus.BAD_REQUEST);
        }
    }


}
