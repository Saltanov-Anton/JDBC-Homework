import DAO.EmployeeDAO;
import DAO.EmployeeDAOImpl;
import model.Employee;

import java.sql.*;

public class Application {


    public static void main(String[] args) {
        try(final Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM employee WHERE id = (?)")) {
            preparedStatement.setInt(1, 2);
            ResultSet resultSet = preparedStatement.executeQuery();

            Employee employee = new Employee();

            while (resultSet.next()){
                employee.setId(resultSet.getInt("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(Integer.parseInt(resultSet.getString("age")));
                employee.setCityId(resultSet.getInt("city"));
                System.out.println(employee);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try (final Connection connection = getConnection()) {
            EmployeeDAO employeeDAO = new EmployeeDAOImpl();
            employeeDAO.saveEmployee(connection, new Employee("Vasya", "Obama", "m", 21));

            employeeDAO.getAllEmployee(connection).forEach(System.out::println);

            System.out.println(employeeDAO.getEmployeeById(connection, 7));

            Employee employee = employeeDAO.getEmployeeById(connection, 7);
            employee.setFirstName("New name");
            employeeDAO.editEmployeeById(connection, employee, 7);

            System.out.println(employeeDAO.getEmployeeById(connection, 7));

            System.out.println(employeeDAO.getAllEmployee(connection));

            employeeDAO.deleteEmployeeById(connection, 7);

            System.out.println(employeeDAO.getAllEmployee(connection));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection getConnection() {
        final String USER = "postgres";
        final String PASSWORD = "33739";
        final String URL = "jdbc:postgresql://localhost:5432/skypro";

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
