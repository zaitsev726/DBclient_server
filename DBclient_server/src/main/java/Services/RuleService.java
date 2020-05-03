package Services;

import Entities.Library;
import Entities.Rule;

import java.util.List;

public interface RuleService {
    Rule addRule(Rule rule);
    void delete(Long id);
    Rule getById(Long id);
    Rule editRule(Rule rule);
    List<Rule> getAll();
}
