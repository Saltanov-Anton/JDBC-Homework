package DAO;

import model.Employee;


import java.util.List;

public interface EmployeeDAO {

    void saveEmployee(Employee employee);

    Employee getEmployeeById(int id);

    List<Employee> getAllEmployee();

    void editEmployeeById(Employee employee);

    void deleteEmployee(Employee employee);
}
