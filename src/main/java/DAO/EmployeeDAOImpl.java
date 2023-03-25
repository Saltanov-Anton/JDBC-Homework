package DAO;

import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {


    @Override
    public void saveEmployee(Connection connection, Employee employee) {
        try(PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO employee (first_name, last_name, gender, age) " +
                "VALUES ((?), (?), (?), (?))")) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getEmployeeById(Connection connection, int id) {
        Employee employee = new Employee();
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id=(?)")){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                employee.setId(resultSet.getInt("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(Integer.parseInt(resultSet.getString("age")));
                employee.setCityId(resultSet.getInt("city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployee(Connection connection) {
        List<Employee> employee = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee")){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Integer id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = Integer.parseInt(resultSet.getString("age"));
                Integer city = resultSet.getInt("city");
                employee.add(new Employee(id, firstName, lastName, gender ,age, city));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public Employee editEmployeeById(Connection connection, Employee employee, int id) {
        try(PreparedStatement statement = connection.prepareStatement("UPDATE employee SET first_name = (?), last_name = (?), gender = (?), " +
                "age = (?) WHERE id = (?)")){
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, id);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public void deleteEmployeeById(Connection connection, int id) {
        try(PreparedStatement statement = connection.prepareStatement("DELETE FROM employee WHERE id = (?)")){
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
