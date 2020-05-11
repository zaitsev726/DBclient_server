package Appli.Services.Impl;

import Appli.Entities.Librarian;
import Appli.Entities.Library;
import Appli.Services.LibrarianService;

import javax.persistence.*;
import java.util.List;

public class LibrarianServiceImpl implements LibrarianService {
    EntityManagerFactory emf;
    public LibrarianServiceImpl(){
        emf = Persistence.createEntityManagerFactory("model");
    }

    @Override
    public Librarian save(Librarian librarian) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        librarian = em.merge(librarian);
        em.getTransaction().commit();
        em.close();
        return librarian;
    }

    @Override
    public void update(Librarian librarian) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(librarian);
            em.getTransaction().commit();
            em.close();
        }catch (RollbackException e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public void delete(long id) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Query query = em.createQuery("delete from Librarian l where l.id_librarian= :id_lib");
            query.setParameter("id_lib", id);
            query.executeUpdate();
            em.getTransaction().commit();
            em.close();
        }catch (RollbackException e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public List<Librarian> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Librarian> librarians = em.createQuery("select librarians from Librarian librarians", Librarian.class).getResultList();
        em.close();
        return librarians;
    }

    @Override
    public Librarian findById(long id) {
        EntityManager em = emf.createEntityManager();
        Librarian librarian = em.createQuery("select librarians from Librarian librarians where librarians.id_librarian = :id_lib", Librarian.class)
                .setParameter("id_lib", id)
                .getSingleResult();
        em.close();
        return librarian;
    }

    @Override
    public List<Librarian> findByIdLibrary(long libID) {
        EntityManager em = emf.createEntityManager();
        List<Librarian> librarians = em.createQuery("select librarians from Librarian librarians where librarians.id_library = :id_lib", Librarian.class)
                .setParameter("id_lib", libID)
                .getResultList();
        em.close();
        return librarians;

    }

    @Override
    public List<Librarian> findByHallNum(long hallNum) {
        EntityManager em = emf.createEntityManager();
        List<Librarian> librarians = em.createQuery("select librarians from Librarian librarians where librarians.hall_num = :hall_num", Librarian.class)
                .setParameter("hall_num", hallNum)
                .getResultList();
        em.close();
        return librarians;
    }

    @Override
    public List<Librarian> findByIdLibraryAndHallNum(long libID, long hallNum) {
        EntityManager em = emf.createEntityManager();
        List<Librarian> librarians = em.createQuery("select librarians from Librarian librarians where librarians.id_library =:id_lib and librarians.hall_num =:hall_num", Librarian.class)
                .setParameter("id_lib", libID)
                .setParameter("hall_num", hallNum)
                .getResultList();
        em.close();
        return librarians;
    }
}
