package Appli.Services.Impl;

import Appli.Entities.*;
import Appli.Services.IssuedBookService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IssuedBookServiceImpl implements IssuedBookService {
    EntityManagerFactory emf;
    public IssuedBookServiceImpl(){
        emf = Persistence.createEntityManagerFactory("model");
    }

    @Override
    public void save(IssuedBook issuedBook) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(issuedBook);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Long id_record) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Query query = em.createQuery("delete from IssuedBook i where i.id_record= :id_rec");
            query.setParameter("id_rec", id_record);
            query.executeUpdate();
            em.getTransaction().commit();
            em.close();
        }catch (RollbackException e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public IssuedBook update(IssuedBook issuedBook) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            issuedBook = em.merge(issuedBook);
            em.getTransaction().commit();
            em.close();
        }catch (RollbackException e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return issuedBook;
    }

    @Override
    public IssuedBook findById(Long id) {
        EntityManager em = emf.createEntityManager();
        IssuedBook issuedBook = em.createQuery("select i from IssuedBook i where i.id_record = :id_rec", IssuedBook.class)
                .setParameter("id_rec", id)
                .getSingleResult();
        em.close();
        return issuedBook;
    }

    @Override
    public List<IssuedBook> findAll() {
        EntityManager em = emf.createEntityManager();
        List<IssuedBook> issuedBooks = em.createQuery("select i from IssuedBook i", IssuedBook.class)
                .getResultList();
        em.close();
        return issuedBooks;
    }

    @Override
    public List<IssuedBook> findByIdReader(Long id_reader) {
        EntityManager em = emf.createEntityManager();
        List<IssuedBook> issuedBooks = em.createQuery("select i from IssuedBook i where i.id_reader = :id", IssuedBook.class)
                .setParameter("id", id_reader)
                .getResultList();
        em.close();
        return issuedBooks;
    }

    @Override
    public List<IssuedBook> findByIdEdition(Long id_edition) {
        EntityManager em = emf.createEntityManager();
        List<IssuedBook> issuedBooks = em.createQuery("select i from IssuedBook i where i.id_edition = :id", IssuedBook.class)
                .setParameter("id", id_edition)
                .getResultList();
        em.close();
        return issuedBooks;
    }

    @Override
    public List<IssuedBook> findReturned() {
        EntityManager em = emf.createEntityManager();
        List<IssuedBook> issuedBooks = em.createQuery("select i from IssuedBook i where i.is_returned = true", IssuedBook.class)
                .getResultList();
        em.close();
        return issuedBooks;
    }

    @Override
    public List<IssuedBook> findNotReturned() {
        EntityManager em = emf.createEntityManager();
        List<IssuedBook> issuedBooks = em.createQuery("select i from IssuedBook i where i.is_returned = false", IssuedBook.class)
                .getResultList();
        em.close();
        return issuedBooks;
    }

    @Override
    public List<IssuedBook> findByIdReaderAndReturned(Long id_reader) {
        EntityManager em = emf.createEntityManager();
        List<IssuedBook> issuedBooks = em.createQuery("select i from IssuedBook i where i.is_returned = true and i.id_reader = :id", IssuedBook.class)
                .setParameter("id", id_reader)
                .getResultList();
        em.close();
        return issuedBooks;
    }

    @Override
    public List<IssuedBook> findByIdReaderAndNotReturned(Long id_reader) {
        EntityManager em = emf.createEntityManager();
        List<IssuedBook> issuedBooks = em.createQuery("select i from IssuedBook i where i.is_returned = false and i.id_reader = :id", IssuedBook.class)
                .setParameter("id", id_reader)
                .getResultList();
        em.close();
        return issuedBooks;
    }

    @Override
    public List<IssuedBook> findByIdLibrarian(Long id_librarian) {
        EntityManager em = emf.createEntityManager();
        List<IssuedBook> issuedBooks = em.createQuery("select i from IssuedBook i where i.id_librarian = :id", IssuedBook.class)
                .setParameter("id", id_librarian)
                .getResultList();
        em.close();
        return issuedBooks;
    }

    @Override
    public List<IssuedBook> findByIdLibrarianAndReturned(Long id_librarian) {
        EntityManager em = emf.createEntityManager();
        List<IssuedBook> issuedBooks = em.createQuery("select i from IssuedBook i where i.id_librarian = :id and i.is_returned = true ", IssuedBook.class)
                .setParameter("id", id_librarian)
                .getResultList();
        em.close();
        return issuedBooks;
    }

    @Override
    public List<IssuedBook> findByIdLibrarianAndNotReturned(Long id_librarian) {
        EntityManager em = emf.createEntityManager();
        List<IssuedBook> issuedBooks = em.createQuery("select i from IssuedBook i where i.id_librarian = :id and i.is_returned = false ", IssuedBook.class)
                .setParameter("id", id_librarian)
                .getResultList();
        em.close();
        return issuedBooks;
    }

    @Override
    public List<IssuedBook> findByIdEditionAndIdLibrarian(Long id_edition, Long id_librarian) {
        EntityManager em = emf.createEntityManager();
        List<IssuedBook> issuedBooks = em.createQuery("select i from IssuedBook i where i.id_edition = :id_edition and i.id_librarian = :id_librarian ", IssuedBook.class)
                .setParameter("id_edition", id_edition)
                .setParameter("id_librarian", id_librarian)
                .getResultList();
        em.close();
        return issuedBooks;
    }

    @Override
    public List<IssuedBook> findByMoreDateExtradition(Date dateExtradition) {
        EntityManager em = emf.createEntityManager();
        List<IssuedBook> issuedBooks = em.createQuery("select i from IssuedBook i where i.date_extradition >= :date_extradition ", IssuedBook.class)
                .setParameter("date_extradition", dateExtradition)
                .getResultList();
        em.close();
        return issuedBooks;
    }

    @Override
    public List<IssuedBook> findByLessDateExtradition(Date dateExtradition) {
        EntityManager em = emf.createEntityManager();
        List<IssuedBook> issuedBooks = em.createQuery("select i from IssuedBook i where i.date_extradition < :date_extradition ", IssuedBook.class)
                .setParameter("date_extradition", dateExtradition)
                .getResultList();
        em.close();
        return issuedBooks;
    }

    @Override
    public List<IssuedBook> findByMoreDateReturn(Date dateReturn) {
        EntityManager em = emf.createEntityManager();
        List<IssuedBook> issuedBooks = em.createQuery("select i from IssuedBook i where i.date_return >= :date_return ", IssuedBook.class)
                .setParameter("date_return", dateReturn)
                .getResultList();
        em.close();
        return issuedBooks;
    }

    @Override
    public List<IssuedBook> findByLessDateReturn(Date dateReturn) {
        EntityManager em = emf.createEntityManager();
        List<IssuedBook> issuedBooks = em.createQuery("select i from IssuedBook i where i.date_return < :date_return ", IssuedBook.class)
                .setParameter("date_return", dateReturn)
                .getResultList();
        em.close();
        return issuedBooks;
    }

    @Override
    public List<IssuedBook> findBetweenDates(Date dateExtradition, Date dateReturn) {
        EntityManager em = emf.createEntityManager();
        List<IssuedBook> issuedBooks = em.createQuery("select i from IssuedBook i where i.date_extradition >= :date_extradition and i.date_return <= :date_return ", IssuedBook.class)
                .setParameter("date_extradition", dateExtradition)
                .setParameter("date_return", dateReturn)
                .getResultList();
        em.close();
        return issuedBooks;
    }

    @Override
    public boolean isReturned(Long id_edition) {
        EntityManager em = emf.createEntityManager();
        List<IssuedBook> issuedBooks = em.createQuery("select i from IssuedBook i where i.id_edition = :id", IssuedBook.class)
                .setParameter("id", id_edition)
                .getResultList();
        for(IssuedBook book: issuedBooks){
            if(!book.isIs_returned())
                return false;
        }
        return true;
    }

    @Override
    public List<AllReader> findReadersWithTitle(String title){
        EntityManager em = emf.createEntityManager();
        List<AllReader> readers = new ArrayList<>();
        em.getTransaction().begin();
        List<Characteristic> characteristics = em.createQuery("select c from Characteristic c where c.title = :title", Characteristic.class)
                .setParameter("title", title)
                .getResultList();

        for(Characteristic c: characteristics){
            readers.addAll(em.createQuery("select a from AllReader a join IssuedBook i on a.id_reader = i.id_reader where i.id_edition = :id and i.is_returned = false ", AllReader.class)
            .setParameter("id",c.getId_edition())
            .getResultList());
        }
        return readers;
    }

    public List<AllReader> findReadersWithType(String type){
        EntityManager em = emf.createEntityManager();
        List<AllReader> readers = new ArrayList<>();
        em.getTransaction().begin();
        List<Characteristic> characteristics = em.createQuery("select c from Characteristic c where c.type_edition = :type", Characteristic.class)
                .setParameter("type", type)
                .getResultList();

        for(Characteristic c: characteristics){
            readers.addAll(em.createQuery("select a from AllReader a join IssuedBook i on a.id_reader = i.id_reader where i.id_edition = :id and i.is_returned = false ", AllReader.class)
            .setParameter("id",c.getId_edition())
            .getResultList());
        }
        return readers;
    }
}
