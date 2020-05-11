package Appli.Services.Impl;

import Appli.Entities.AllReader;
import Appli.Entities.Librarian;
import Appli.Entities.Library;
import Appli.Entities.Types.Pensioner;
import Appli.Services.LibrarianService;
import Appli.Services.LibraryService;

import javax.persistence.*;
import java.util.List;

public class LibraryServiceImpl implements LibraryService {

    EntityManagerFactory emf;

    public LibraryServiceImpl(){
        emf = Persistence.createEntityManagerFactory("model");
    }

    @Override
    public void save(Library library) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(library);
        em.getTransaction().commit();
        em.close();
        System.out.println(library);
    }

    @Override
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Query query = em.createQuery("delete from Library l where l.id_library= :id_lib");
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
    public void update(Library library) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(library);
            em.getTransaction().commit();
            em.close();
        }catch (RollbackException e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public Library getById(Long id) {
        EntityManager em = emf.createEntityManager();
        Library library = em.createQuery("select libraries from Library libraries where libraries.id_library = :id", Library.class)
                .setParameter("id", id)
                .getSingleResult();
       // System.out.println(library.getLibrarians());
        em.close();
        return library;
    }

    @Override
    public Library getLibrarians(Long id) {
        EntityManager em = emf.createEntityManager();
        Library library = em.createQuery("select libraries from Library libraries where libraries.id_library = :id", Library.class)
                .setParameter("id", id)
                .getSingleResult();
        System.out.println(library.getLibrarians());
        em.close();
        return library;
    }

    @Override
    public List<Library> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Library> libraries = em.createQuery("select libraries from Library libraries", Library.class).getResultList();
        em.close();
        return libraries;
    }


    @Override
    public List<Library> findByHallNum(long hallNum) {
        EntityManager em = emf.createEntityManager();
        List<Library> libraries = em.createQuery("select libraries from Library libraries where  libraries.halls_num = :hallNum", Library.class)
                .setParameter("hallNum",hallNum)
                .getResultList();
        em.close();
        return libraries;
    }

    @Override
    public List<Library> findByIdAndHallNum(long id, long hallNum) {
        EntityManager em = emf.createEntityManager();
        List<Library> libraries = em.createQuery("select libraries from Library libraries where  libraries.id_library = :id_lib and libraries.halls_num = :hall_num", Library.class)
                .setParameter("id_lib",id)
                .setParameter("hall_num",hallNum)
                .getResultList();
        em.close();
        return libraries;
    }
}