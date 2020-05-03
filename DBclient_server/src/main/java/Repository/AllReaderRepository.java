package Repository;

import Entities.AllReader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AllReaderRepository extends CrudRepository<AllReader, Long> {
    List<AllReader> findByName(String name);
    AllReader findById_reader(Long id_reader);
}
