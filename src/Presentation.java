import java.util.ArrayList;
import java.util.Scanner;

public class Presentation {
    static Scanner sc = new Scanner(System.in);
    private static boolean loopCondition = true;

    public static void startInteraction() {

        System.out.println("-----Welcome to EMS System---------");

        while (loopCondition) {
            System.out.println();
            System.out.println("To add a new Employee press 1");
            System.out.println("To search for an existing employee press 2");
            System.out.println("To edit an existing employee press 3");
            System.out.println("To view all employees press 4");
            System.out.println("To delete Employee press 5");
            System.out.println("To Exit out of EMS press 6");
            int id;
            String name;
            String designation;
            String department;
            Employee employee;

            int choice = sc.nextInt();
            switch (choice) {

                case 1:
                    System.out.println("Enter id, name, designation and department for the new Employee seperated by space");
                    id = sc.nextInt();
                    name = sc.next();
                    designation = sc.next();
                    department = sc.next();
                    employee = EmployeeRepository.addEmployee(id, name, designation, department);
                    System.out.println("Details of new Employee: " + employee);
                    break;

                case 2:
                    System.out.println("Enter id of the required Employee");
                    id = sc.nextInt();
                    employee = EmployeeRepository.getEmployee(id);
                    if (employee == null){
                        System.out.println("Employee does not exist");
                    }else {
                        System.out.println("Details of the Employee: " + employee);
                    }
                    break;

                case 3:
                    System.out.println("Enter the id of the Employee whose details are to be updated");
                    id = sc.nextInt();
                    System.out.println("Enter the updated name, designation and department");
                    name = sc.next();
                    designation = sc.next();
                    department = sc.next();
                    employee = EmployeeRepository.updateEmployee(id, name, designation, department);
                    if(employee == null){
                        System.out.println("Employee does not exist");
                    }else {
                        System.out.println(employee);
                    }
                    break;

                case 4:
                    ArrayList<Employee> employees = EmployeeRepository.allEmployees();
                    for (Employee e: employees) {
                        System.out.println(e);
                    }
                    break;

                case 5:
                    System.out.println("Enter id of the Employee to Delete");
                    id = sc.nextInt();
                    employee = EmployeeRepository.deleteEmployee(id);
                    if (employee!= null) {
                        System.out.println("Employee Deleted");
                    }else{
                        System.out.println("Employee does not exist");
                    }
                    break;

                case 6:
                    loopCondition = false;
                    System.out.println("Exiting out of EMS...");
                    break;

                default:
                    System.out.println("Invalid input");
            }


        }
    }


}
