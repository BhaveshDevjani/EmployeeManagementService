import java.util.*;

public class EmployeeService {

    EmployeeDao employeeDao = new EmployeeDaoImpl();


    boolean addEmployee(Employee employee) {

        return employeeDao.insertEmployee(employee);
    }

    Employee getEmployee(int id) {

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
