package Appli.Services;

import Appli.Entities.AllReader;

import java.util.List;

public interface AllReaderService {
    List<AllReader> findAll();
    AllReader findById(Long id);
    AllReader save(AllReader reader);
    void update(AllReader reader);
    void delete(AllReader reader);
    List<AllReader> findByNameAndSurnameAndPatronymic(String name, String surname, String patronymic);
    List<AllReader> findByNameAndPatronymic(String name,String patronymic);
    List<AllReader> findByNameAndSurname(String name,String surname);
    List<AllReader> findBySurnameAndPatronymic(String surname,String patronymic);
    List<AllReader> findByName(String name);
    List<AllReader> findBySurname(String surname);
    List<AllReader> findByPatronymic(String patronymic);
}
