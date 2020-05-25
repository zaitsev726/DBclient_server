package Application.Services;

import Application.Entities.Types.AbstractReader;

public interface AbstractReaderService {
    void save(AbstractReader reader);
    void delete(AbstractReader reader);
}
