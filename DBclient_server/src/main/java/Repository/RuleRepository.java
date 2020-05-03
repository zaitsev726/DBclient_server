package Repository;

import Entities.AllReader;
import Entities.Rule;
import org.springframework.data.repository.CrudRepository;

public interface RuleRepository extends CrudRepository<Rule, Long> {
    Rule findById_rule(Long id_rule);
}
