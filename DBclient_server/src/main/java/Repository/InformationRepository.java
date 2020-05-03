package Repository;

import Entities.Information;
import org.springframework.data.repository.CrudRepository;


public interface InformationRepository extends CrudRepository<Information, Long> {
    Information findById_record(Long id_record);
}
