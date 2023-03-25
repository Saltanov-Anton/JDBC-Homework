package DAO;

import model.Employee;

import java.sql.Connection;
import java.util.List;

public interface EmployeeDAO {

    void saveEmployee(Connection connection, Employee employee);

    Employee getEmployeeById(Connection connection, int id);

    List<Employee> getAllEmployee(Connection connection);

    Employee editEmployeeById(Connection connection, Employee employee, int id);

    void deleteEmployeeById(Connection connection, int id);
}
