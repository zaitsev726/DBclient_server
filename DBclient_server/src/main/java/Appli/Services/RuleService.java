package Appli.Services;

import Appli.Entities.Library;
import Appli.Entities.Rule;

import java.util.List;

public interface RuleService {
    void save(Rule rule);
    void delete(Long id);
    Rule update(Rule rule);

    Rule findById(Long id);
    List<Rule> findAll();
    List<Rule> findByIdEdition(long id_edition);
}
