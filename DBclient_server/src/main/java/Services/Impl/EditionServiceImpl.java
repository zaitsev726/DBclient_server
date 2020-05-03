package Services.Impl;

import Entities.Edition;
import Repository.EditionRepository;
import Services.EditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditionServiceImpl implements EditionService {
    @Autowired
    private EditionRepository editionRepository;

    @Override
    public Edition addEdition(Edition edition) {
        Edition savedEdition = editionRepository.save(edition);
        return savedEdition;
    }

    @Override
    public void delete(Long id) {
        editionRepository.deleteById(id);
    }

    @Override
    public Edition getById(Long id) {
        return editionRepository.findById_edition(id);
    }

    @Override
    public Edition editEdition(Edition edition) {
        editionRepository.deleteById(edition.getId_edition());
        return editionRepository.save(edition);
    }

    @Override
    public List<Edition> getAll() {
        return (List<Edition>) editionRepository.findAll();
    }

}
