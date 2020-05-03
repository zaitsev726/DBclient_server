package Services;

import Entities.AllReader;

import java.util.List;

public interface AllReaderService {
    List<AllReader> findAll();
    AllReader findById(Long id);
    AllReader save(AllReader reader);
    void delete(AllReader reader);
}
