package Services.Impl;

import Entities.Characteristic;
import Repository.CharacteristicRepository;
import Services.CharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CharacteristicServiceImpl implements CharacteristicService {
    @Autowired
    private CharacteristicRepository characteristicRepository;

    @Override
    public Characteristic addCharacteristic(Characteristic characteristic) {
        return characteristicRepository.save(characteristic);
    }

    @Override
    public void delete(Long id) {
        characteristicRepository.deleteById(id);
    }

    @Override
    public Characteristic getById(Long id) {
        return characteristicRepository.findById_edition(id);
    }

    @Override
    public Characteristic editCharacteristic(Characteristic characteristic) {
        characteristicRepository.deleteById(characteristic.getId_edition());
        return characteristicRepository.save(characteristic);
    }

    @Override
    public List<Characteristic> getAll() {
        return (List<Characteristic>) characteristicRepository.findAll();
    }

}
