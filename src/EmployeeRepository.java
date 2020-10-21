import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeRepository {

    static HashMap<Integer, Employee> employees = new HashMap<>();

    static Employee addEmployee(Integer id, String name, String designation, String department) {
        Employee employee = new Employee(id, name, designation, department);
        employees.put(id, employee);
        return employee;
    }

    static Employee getEmployee(Integer id) {
        return employees.get(id);
    }

    static Employee updateEmployee(int id, String name, String designation, String department){
        if (employees.containsKey(id)) {
            Employee employee = new Employee(id, name, designation, department);
            employees.put(id, employee);
            return employee;
        }else {
            return null;
        }
    }

    static ArrayList<Employee> allEmployees() {
        return new ArrayList(employees.values());
//        return null;
    }

    public static Employee deleteEmployee(int id) {
        return employees.remove(id);
    }
}
