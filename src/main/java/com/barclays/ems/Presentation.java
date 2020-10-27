package com.barclays.ems;

import com.barclays.ems.model.Employee;
import com.barclays.ems.service.EmployeeService;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Presentation {
    static Scanner sc = new Scanner(System.in);
    private static boolean loopCondition = true;

    public static void startInteraction() {

        System.out.println("-----Welcome to EMS System---------");
        EmployeeService service = new EmployeeService();

        while (loopCondition) {
            System.out.println();
            System.out.println("To add a new com.barclays.ems.model.Employee press 1");
            System.out.println("To search for an existing employee press 2");
            System.out.println("To edit an existing employee press 3");
            System.out.println("To view all employees press 4");
            System.out.println("To delete com.barclays.ems.model.Employee press 5");
            System.out.println("To Exit out of EMS press 6");
            int id;
            String name;
            String designation;
            String department;
            Employee employee;
            boolean result;

            try {

                int choice = sc.nextInt();
                switch (choice) {

                    case 1:

                        System.out.println("Enter id, name, designation and department for the new Employee separated by space");
                        id = sc.nextInt();
                        name = sc.next();
                        designation = sc.next();
                        department = sc.next();

                        employee = new Employee();

                        employee.setId(id);
                        employee.setName(name);
                        employee.setDesignation(designation);
                        employee.setDepartment(department);

                        result = service.addEmployee(employee);
                        if (result) {
                            System.out.println("Insertion Successful");
                        } else {
                            System.out.println("Not successful, Please try again");
                        }
                        break;

                    case 2:

                        System.out.println("Enter id of the required Employee");
                        id = sc.nextInt();
                        employee = service.getEmployee(id);

                        if (employee != null) {
                            System.out.println(employee);
                        } else {
                            System.out.println("Unsuccessful, employee not found");
                        }

                        break;

                    case 3:

                        System.out.println("Enter the id of the Employee whose details are to be updated");
                        id = sc.nextInt();
                        System.out.println("Enter the updated name, designation and department");
                        name = sc.next();
                        designation = sc.next();
                        department = sc.next();

                        employee = new Employee();
                        employee.setId(id);
                        employee.setName(name);
                        employee.setDesignation(designation);
                        employee.setDepartment(department);

                        result = service.updateEmployee(employee);

                        if (result) {
                            System.out.println("Update Successful");
                        } else {
                            System.out.println("Update unsuccessful, employee with given id not found");
                        }

                        break;

                    case 4:

                        HashSet<Employee> employees = service.allEmployees();

                        for (Employee e : employees) {
                            System.out.println(e);
                        }
                        break;

                    case 5:

                        System.out.println("Enter id of the Employee to Delete");
                        id = sc.nextInt();

                        result = service.deleteEmployee(id);

                        if (result) {
                            System.out.println("Delete Successful");
                        } else {
                            System.out.println("Delete unsuccessful, employee not found");
                        }

                        break;

                    case 6:
                        loopCondition = false;
                        System.out.println("Exiting out of EMS...");
                        break;

                    default:
                        System.out.println("Invalid input");
                }


            } catch (InputMismatchException throwable) {
                sc.nextLine();
                throwable.printStackTrace();
                System.out.println("Mismatch input, please try again");
            }
        }
    }


}
