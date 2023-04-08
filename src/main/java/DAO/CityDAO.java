package DAO;

import model.City;

import java.util.List;

public interface CityDAO {

    void saveCity(City city);

    City getCityById(Integer id);

    List<City> getAllCity();

    void editCityById(City city);

    void deleteCity(City city);
}
