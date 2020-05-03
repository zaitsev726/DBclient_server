package Services.Impl;

import Entities.AllReader;
import Repository.AllReaderRepository;
import Services.AllReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
public class AllReaderRepositoryImpl implements AllReaderService {

    @Autowired
    AllReaderRepository allReaderRepository;

    @Override
    public List<AllReader> findAll() {
        return (List<AllReader>) allReaderRepository.findAll();
    }

    @Override
    public AllReader findById(Long id) {
        return allReaderRepository.findById_reader(id);
    }

    @Override
    public AllReader save(AllReader reader) {
        return allReaderRepository.save(reader);
    }

    @Override
    public void delete(AllReader reader) {
        allReaderRepository.delete(reader);
    }
}
