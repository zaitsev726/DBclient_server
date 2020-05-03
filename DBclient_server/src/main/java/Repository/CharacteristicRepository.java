package Repository;

import Entities.AllReader;
import Entities.Characteristic;
import org.springframework.data.repository.CrudRepository;

public interface CharacteristicRepository extends CrudRepository<Characteristic, Long> {
    Characteristic findById_edition(Long id_edition);
}
