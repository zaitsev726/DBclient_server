package Application.Services.Impl;

import Application.Entities.Information;
import Application.Services.InformationService;

import javax.persistence.*;
import java.util.List;

public class InformationServiceImpl implements InformationService {
    EntityManagerFactory emf;

    public InformationServiceImpl(EntityManagerFactory emf){ this.emf = emf; }

    @Override
    public void save(Information information) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(information);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Query query = em.createQuery("delete from Information i where i.id_record= :id_rec");
            query.setParameter("id_rec", id);
            query.executeUpdate();
            em.getTransaction().commit();
            em.close();
        }catch (RollbackException e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public Information update(Information information) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        information = em.merge(information);
        em.getTransaction().commit();
        em.close();
        return information;
    }

    @Override
    public Information findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Information information = em.createQuery("select i from Information i where i.id_record = :id_rec", Information.class)
                .setParameter("id_rec", id)
                .getSingleResult();
        em.close();
        return information;
    }

    @Override
    public List<Information> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Information> information = em.createQuery("select i from Information i", Information.class)
                .getResultList();
        em.close();
        return information;
    }

    @Override
    public List<Information> findByIdEdition(Long id) {
        EntityManager em = emf.createEntityManager();
        List<Information> information = em.createQuery("select i from Information i where i.id_edition = :id_ed", Information.class)
                .setParameter("id_ed", id)
                .getResultList();
        em.close();
        return information;
    }

    @Override
    public List<Information> findByAuthor(String author) {
        EntityManager em = emf.createEntityManager();
        List<Information> information = em.createQuery("select i from Information i where i.author = :author", Information.class)
                .setParameter("author", author)
                .getResultList();
        em.close();
        return information;
    }

    @Override
    public List<Information> findByComposition(String composition) {
        EntityManager em = emf.createEntityManager();
        List<Information> information = em.createQuery("select i from Information i where i.composition = :composition", Information.class)
                .setParameter("composition", composition)
                .getResultList();
        em.close();
        return information;
    }

    @Override
    public List<Information> findByAuthorAndComposition(String author, String composition) {
        EntityManager em = emf.createEntityManager();
        List<Information> information = em.createQuery("select i from Information i where i.author = :author and i.composition = :composition", Information.class)
                .setParameter("author", author)
                .setParameter("composition", composition)
                .getResultList();
        em.close();
        return information;
    }

    @Override
    public void insertStartInformation(List<Information> informationList) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        for(Information i : informationList){
            em.persist(i);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Information mostPopular() {
        EntityManager em = emf.createEntityManager();
        List<Information> information = em.createQuery("select i from Information i ORDER BY i.popularity DESC ", Information.class)
                .getResultList();
        em.close();
        return information.get(0);
    }
}
