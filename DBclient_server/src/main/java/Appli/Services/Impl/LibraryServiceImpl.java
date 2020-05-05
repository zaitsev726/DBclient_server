package Appli.Services.Impl;

import Appli.Entities.AllReader;
import Appli.Entities.Librarian;
import Appli.Entities.Library;
import Appli.Services.LibrarianService;
import Appli.Services.LibraryService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class LibraryServiceImpl implements LibraryService {

    EntityManagerFactory emf;

    public LibraryServiceImpl(){
        emf = Persistence.createEntityManagerFactory("model");
    }

    @Override
    public Library addLibrary(Library library) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Library getById(Long id) {
        EntityManager em = emf.createEntityManager();
        Library library = em.createQuery("select libraries from Library libraries where libraries.id_library = :id", Library.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return library;
    }

    @Override
    public Library editLibrary(Library library) {
        return null;
    }

    @Override
    public List<Library> getAll() {
        EntityManager em = emf.createEntityManager();
        List<Library> libraries = em.createQuery("select libraries from Library libraries", Library.class).getResultList();
        em.close();
        return libraries;
    }
}
