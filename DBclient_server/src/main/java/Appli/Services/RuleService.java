package Appli.Services;

import Appli.Entities.Library;
import Appli.Entities.Rule;

import java.util.List;

public interface RuleService {
    void save(Library library);
    void delete(Long id);
    void update(Library library);
    Library getLibrarians(Long id);
    Library getById(Long id);
    List<Library> findAll();
    List<Library> findByHallNum(long hallNum);
    List<Library> findByIdAndHallNum(long id, long hallNum);
}
