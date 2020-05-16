package Appli.Services;

import Appli.Entities.Characteristic;
import Appli.Entities.Information;

import java.util.List;

public interface InformationService {
    void save(Information information);
    void delete(Long id);
    Information update(Information information);
    Information findById(Long id);
    List<Information> findAll();

    List<Information> findByIdEdition(Long id);
    List<Information> findByAuthor(String author);
    List<Information> findByComposition(String composition);
    List<Information> findByAuthorAndComposition(String author, String composition);

    Information mostPopular();
    void insertStartInformation(List<Information> informationList);

}
