package Appli.Services.Impl;

import Appli.Entities.AllReader;
import Appli.Entities.Types.Pensioner;
import Appli.Services.AllReaderService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.Reader;
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


        Pensioner pensioner = em.find(Pensioner.class, (long)0);
        reader.setReaderType(pensioner);


        //em.persist(reader);
        reader = em.merge(reader);
        em.getTransaction().commit();
        em.close();
        System.out.println(reader);
        return reader;
    }

    @Override
    public void delete(AllReader reader) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("delete from AllReader e where e.name = :r_name and e.surname = :r_surname and e.patronymic = :r_patronymic");

        query.setParameter("r_name", reader.getName());
        query.setParameter("r_surname", reader.getSurname());
        query.setParameter("r_patronymic", reader.getPatronymic());

        int rowsDeleted = query.executeUpdate();
        System.out.println("Удалено стрчоек " + rowsDeleted);
        em.getTransaction().commit();
        em.close();

    }

    public List<AllReader> findByNameAndSurnameAndPatronymic(String cur_name,String cur_surname, String cur_patronymic){
        EntityManager em = emf.createEntityManager();
        List<AllReader> readers = em.createQuery("select readers from AllReader readers where readers.name = :r_name and readers.surname = :r_surname and readers.patronymic = :r_patronymic", AllReader.class)
                .setParameter("r_name", cur_name)
                .setParameter("r_surname", cur_surname)
                .setParameter("r_patronymic", cur_patronymic)
                .getResultList();
        return readers;

    }

    @Override
    public List<AllReader> findByNameAndPatronymic(String cur_name,String cur_patronymic) {
        EntityManager em = emf.createEntityManager();
        List<AllReader> readers = em.createQuery("select readers from AllReader readers where readers.name = :r_name and readers.patronymic = :r_patronymic", AllReader.class)
                .setParameter("r_name", cur_name)
                .setParameter("r_patronymic", cur_patronymic)
                .getResultList();
        return readers;
    }

    @Override
    public List<AllReader> findByNameAndSurname(String cur_name, String cur_surname) {
        EntityManager em = emf.createEntityManager();
        List<AllReader> readers = em.createQuery("select readers from AllReader readers where readers.name = :r_name and readers.surname = :r_surname", AllReader.class)
                .setParameter("r_name", cur_name)
                .setParameter("r_surname", cur_surname)
                .getResultList();
        return readers;
    }

    @Override
    public List<AllReader> findBySurnameAndPatronymic(String cur_surname,String cur_patronymic) {
        EntityManager em = emf.createEntityManager();
        List<AllReader> readers = em.createQuery("select readers from AllReader readers where readers.surname = :r_surname and readers.patronymic = :r_patronymic", AllReader.class)
                .setParameter("r_surname", cur_surname)
                .setParameter("r_patronymic", cur_patronymic)
                .getResultList();
        return readers;
    }

    @Override
    public List<AllReader> findByName(String cur_name) {
        EntityManager em = emf.createEntityManager();
        List<AllReader> readers = em.createQuery("select readers from AllReader readers where readers.name = :r_name", AllReader.class)
                .setParameter("r_name", cur_name)
                .getResultList();
        return readers;
    }

    @Override
    public List<AllReader> findBySurname(String cur_surname) {
        EntityManager em = emf.createEntityManager();
        List<AllReader> readers = em.createQuery("select readers from AllReader readers where readers.surname = :r_surname ", AllReader.class)
                .setParameter("r_surname", cur_surname)
                .getResultList();
        return readers;
    }

    @Override
    public List<AllReader> findByPatronymic(String cur_patronymic) {
        EntityManager em = emf.createEntityManager();
        List<AllReader> readers = em.createQuery("select readers from AllReader readers where readers.patronymic = :r_patronymic", AllReader.class)
                .setParameter("r_patronymic", cur_patronymic)
                .getResultList();
        return readers;
    }
}
