package Services;

import Entities.Edition;

import java.util.List;

public interface EditionService {
    Edition addEdition(Edition edition);
    void delete(Long id);
    Edition getById(Long id);
    Edition editEdition(Edition edition);
    List<Edition> getAll();
}
