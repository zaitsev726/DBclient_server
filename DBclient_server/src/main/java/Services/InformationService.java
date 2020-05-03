package Services;

import Entities.Edition;
import Entities.Information;

import java.util.List;

public interface InformationService {
    Information addInformation(Information information);
    void delete(Long id);
    Information getById(Long id);
    Information editInformation(Information information);
    List<Information> getAll();
}
