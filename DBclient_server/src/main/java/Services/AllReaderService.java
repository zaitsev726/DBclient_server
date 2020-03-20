package Services;

import Entities.AllReader;

import java.util.List;

public interface AllReaderService {
    List<AllReader> findAll();
    List<AllReader> findWithType();
    AllReader findById(int id);
    AllReader save(AllReader reader);
    void delete(AllReader reader);
    
}
