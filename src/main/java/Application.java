import DAO.CityDAOImpl;
import DAO.EmployeeHibernateDAOImpl;
import model.City;
import model.Employee;

import java.sql.*;

public class Application {


    public static void main(String[] args) {
        taskWithHibernate();

    }

    private static void taskWithHibernate() {
        EmployeeHibernateDAOImpl employeeDAO = new EmployeeHibernateDAOImpl();
        CityDAOImpl cityDAO = new CityDAOImpl();
        City city = new City("Chita");
        Employee employee = new Employee("Misha", "mihailov", "m", 54, city);
       // cityDAO.getAllCity().forEach(System.out::println);
    //    cityDAO.saveCity(city);

  //      employeeDAO.saveEmployee(employee);

        employeeDAO.getAllEmployee().forEach(System.out::println);

//        Employee employee = employeeDAO.getEmployeeById(12);
//       System.out.println(employee);
//
//        employeeDAO.editEmployeeById(employee);
//
//        employeeDAO.getAllEmployee().forEach(System.out::println);
//
        employeeDAO.deleteEmployee(employee);
//
//        employeeDAO.getAllEmployee().forEach(System.out::println);
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
