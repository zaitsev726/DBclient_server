package Services.Impl;

import Entities.Rule;
import Repository.RuleRepository;
import Services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RuleServiceImpl implements RuleService {
    @Autowired
    RuleRepository RuleRepository;

    @Override
    public Rule addRule(Rule Rule) {
        return RuleRepository.save(Rule);
    }

    @Override
    public void delete(Long id) {
        RuleRepository.deleteById(id);
    }

    @Override
    public Rule getById(Long id) {
        return RuleRepository.findById_rule(id);
    }

    @Override
    public Rule editRule(Rule Rule) {
        RuleRepository.deleteById(Rule.getId_rule());
        return RuleRepository.save(Rule);
    }

    @Override
    public List<Rule> getAll() {
        return (List<Rule>) RuleRepository.findAll();
    }
}
