package Appli.Services;

import Appli.Entities.Characteristic;

import java.util.List;

public interface CharacteristicService {
    Characteristic addCharacteristic(Characteristic characteristic);
    void delete(Long id);
    Characteristic getById(Long id);
    Characteristic editCharacteristic(Characteristic characteristic);
    List<Characteristic> getAll();

}
