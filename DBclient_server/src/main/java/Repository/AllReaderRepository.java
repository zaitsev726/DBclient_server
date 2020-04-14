package Repository;

import Entities.AllReader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AllReaderRepository extends CrudRepository<AllReader, Long> {
    List<AllReader> findByName(String name);
    AllReader findById_reader(Long id_reader);
}
