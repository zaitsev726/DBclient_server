package Appli.Services;

import Appli.Entities.AllReader;
import Appli.Entities.Types.AbstractReader;

public interface AbstractReaderService {
    void save(AbstractReader reader);
    void delete(AbstractReader reader);

}
