import java.util.HashSet;

public interface EmployeeDao {

    boolean insertEmployee(Employee employee);

    Employee getEmployee(int id);

    boolean updateEmployee(Employee employee);

    HashSet<Employee> getAllEmployees();

    boolean deleteEmployee(int id);
}
