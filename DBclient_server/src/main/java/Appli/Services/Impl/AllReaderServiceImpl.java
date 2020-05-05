package Appli.Services.Impl;

import Appli.Entities.AllReader;
import Appli.Entities.Types.Pensioner;
import Appli.Services.AllReaderService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AllReaderServiceImpl implements AllReaderService {
    EntityManagerFactory emf;

    public AllReaderServiceImpl(){
        emf = Persistence.createEntityManagerFactory("model");
    }

    @Override
    public List<AllReader> findAll() {
        EntityManager em = emf.createEntityManager();
        List<AllReader> readers = em.createQuery("select readers from AllReader readers", AllReader.class).getResultList();
        em.close();
        return readers;
    }

    @Override
    public AllReader findById(Long id) {
        EntityManager em = emf.createEntityManager();
        AllReader reader = em.createQuery("select readers from AllReader readers where readers.id_reader = :id", AllReader.class)
                            .setParameter("id", id)
                            .getSingleResult();
        em.close();
        return reader;
    }

    @Override
    public AllReader save(AllReader reader) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //em.persist(reader);
        em.merge(reader);
        em.getTransaction().commit();
       // AllReader savedReader = findById(reader.getId_reader());
        em.close();
        return null;
    }

    @Override
    public void delete(AllReader reader) {

    }
}
