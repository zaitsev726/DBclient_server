package Services.Impl;

import Entities.Information;
import Repository.InformationRepository;
import Services.InformationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InformationServiceImpl implements InformationService {
    @Autowired
    private InformationRepository informationRepository;
    @Override
    public Information addInformation(Information information) {
        return informationRepository.save(information);
    }

    @Override
    public void delete(Long id) {
        informationRepository.deleteById(id);
    }

    @Override
    public Information getById(Long id) {
        return informationRepository.findById_record(id);
    }

    @Override
    public Information editInformation(Information information) {
        informationRepository.deleteById(information.getId_record());
        return informationRepository.save(information);
    }

    @Override
    public List<Information> getAll() {
        return (List<Information>) informationRepository.findAll();
    }

}
