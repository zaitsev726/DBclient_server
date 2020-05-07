package Appli.Services.Impl;

import Appli.Entities.Types.AbstractReader;
import Appli.Services.AbstractReaderService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AbstractReaderServiceImpl implements AbstractReaderService {
    EntityManagerFactory emf;
    public AbstractReaderServiceImpl(){
        emf = Persistence.createEntityManagerFactory("model");
    }
    @Override
    public void save(AbstractReader reader) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(reader);
        em.getTransaction().commit();
    }

    @Override
    public void delete(AbstractReader reader) {

    }
}
