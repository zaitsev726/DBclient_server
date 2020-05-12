package Appli.Services;

import Appli.Entities.Characteristic;

import java.util.List;

public interface CharacteristicService {
    Characteristic save(Characteristic characteristic);
    void delete(Long id);
    Characteristic update(Characteristic characteristic);
    Characteristic findById(Long id);
    List<Characteristic> findAll();
    List<Characteristic> findByType(String type);
    List<Characteristic> findByAuthor(String author);
    List<Characteristic> findByTitle(String title);
    List<Characteristic> findByTypeAndAuthor(String type, String author);
    List<Characteristic> findByTypeAndTitle(String type, String title);
    List<Characteristic> findByAuthorAndTitle(String author, String title);
    List<Characteristic> findByTypeAndAuthorAndTitle(String type, String author, String title);

}
