package DAO;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import java.sql.Connection;
import java.util.List;

public class EmployeeHibernateDAOImpl implements EmployeeDAO{

    @Override
    public void saveEmployee(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return session.get(Employee.class, id);
        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            return session.createQuery("FROM Employee").list();
        }
    }

    @Override
    public void editEmployeeById(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        }
    }

    @Override
    public void deleteEmployee(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }
}
