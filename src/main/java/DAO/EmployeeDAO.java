package DAO;

import model.Employee;

import java.util.List;

public interface EmployeeDAO {

    void saveEmployee(int id, String first_name, String last_name, String gender, int age);

    Employee getEmployeeById(int id);

    List<Employee> getAllEmployee();

    void editEmployeeById(int id);

    void deleteEmployeeById(int id);

}
