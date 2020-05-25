package Application.Services.Impl;

import Application.Entities.Characteristic;
import Application.Services.CharacteristicService;

import javax.persistence.*;
import java.util.List;

public class CharacteristicServiceImpl implements CharacteristicService {
    EntityManagerFactory emf;

    public CharacteristicServiceImpl(){ emf = Persistence.createEntityManagerFactory("model"); }

    @Override
    public Characteristic save(Characteristic characteristic) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        characteristic = em.merge(characteristic);
        em.getTransaction().commit();
        em.close();
        return characteristic;
    }

    @Override
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Query query = em.createQuery("delete from Characteristic c where c.id_edition= :id_edition");
            query.setParameter("id_edition", id);
            query.executeUpdate();
            em.getTransaction().commit();
            em.close();
        }catch (RollbackException e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public Characteristic update(Characteristic characteristic) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        characteristic = em.merge(characteristic);
        em.getTransaction().commit();
        em.close();
        return characteristic;
    }

    @Override
    public Characteristic findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Characteristic characteristic = em.createQuery("select characteristics from Characteristic characteristics where characteristics.id_edition = :id_edition", Characteristic.class)
                .setParameter("id_edition", id)
                .getSingleResult();
        em.close();
        return characteristic;
    }

    @Override
    public List<Characteristic> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Characteristic> characteristics = em.createQuery("select characteristics from Characteristic characteristics", Characteristic.class)
                .getResultList();
        em.close();
        return characteristics;
    }

    @Override
    public List<Characteristic> findByType(String type) {
        EntityManager em = emf.createEntityManager();
        List<Characteristic> characteristics = em.createQuery("select c from Characteristic c where c.type_edition = :type_ed", Characteristic.class)
                .setParameter("type_ed", type)
                .getResultList();
        em.close();
        return characteristics;
    }

    @Override
    public List<Characteristic> findByAuthor(String author) {
        EntityManager em = emf.createEntityManager();
        List<Characteristic> characteristics = em.createQuery("select c from Characteristic c where c.author = :ed_author", Characteristic.class)
                .setParameter("ed_author", author)
                .getResultList();
        em.close();
        return characteristics;
    }

    @Override
    public List<Characteristic> findByTitle(String title) {
        EntityManager em = emf.createEntityManager();
        List<Characteristic> characteristics = em.createQuery("select c from Characteristic c where c.title = :ed_title", Characteristic.class)
                .setParameter("ed_title", title)
                .getResultList();
        em.close();
        return characteristics;
    }

    @Override
    public List<Characteristic> findByTypeAndAuthor(String type, String author) {
        EntityManager em = emf.createEntityManager();
        List<Characteristic> characteristics = em.createQuery("select c from Characteristic c where c.type_edition = :ed_type and c.author = :ed_author", Characteristic.class)
                .setParameter("ed_type", type)
                .setParameter("ed_author", author)
                .getResultList();
        em.close();
        return characteristics;
    }

    @Override
    public List<Characteristic> findByTypeAndTitle(String type, String title) {
        EntityManager em = emf.createEntityManager();
        List<Characteristic> characteristics = em.createQuery("select c from Characteristic c where c.type_edition = :ed_type and c.title = :ed_title", Characteristic.class)
                .setParameter("ed_type", type)
                .setParameter("ed_title", title)
                .getResultList();
        em.close();
        return characteristics;
    }

    @Override
    public List<Characteristic> findByAuthorAndTitle(String author, String title) {
        EntityManager em = emf.createEntityManager();
        List<Characteristic> characteristics = em.createQuery("select c from Characteristic c where c.author = :ed_author and c.title = :ed_title", Characteristic.class)
                .setParameter("ed_author", author)
                .setParameter("ed_title", title)
                .getResultList();
        em.close();
        return characteristics;
    }

    @Override
    public List<Characteristic> findByTypeAndAuthorAndTitle(String type, String author, String title) {
        EntityManager em = emf.createEntityManager();
        List<Characteristic> characteristics = em.createQuery("select c from Characteristic c where c.type_edition = :ed_type and c.author = :ed_author and c.title = :ed_title", Characteristic.class)
                .setParameter("ed_type", type)
                .setParameter("ed_author", author)
                .setParameter("ed_title", title)
                .getResultList();
        em.close();
        return characteristics;
    }
}
