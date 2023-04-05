package DAO;

import model.City;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import java.util.List;

public class CityDAOImpl implements CityDAO {

    @Override
    public void saveCity(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(city);
            transaction.commit();
        }
    }

    @Override
    public City getCityById(Integer id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(City.class, id);
        }
    }

    @Override
    public List<City> getAllCity() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM City").list();
        }
    }

    @Override
    public void editCityById(City city) {
        if (isPresentInDB(city)) {
            try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.update(city);
                transaction.commit();
            }
        } else {
            System.out.println("City не найден");
        }
    }

    @Override
    public void deleteCity(City city) {
        if (isPresentInDB(city)) {
            try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                session.delete(city);
                transaction.commit();
            }
        } else {
            System.out.println("Работник не найден");
        }
    }

    private boolean isPresentInDB(City city) {
        if (city == null || city.getCityId() == null) {
            return false;
        }
        return true;
    }
}
