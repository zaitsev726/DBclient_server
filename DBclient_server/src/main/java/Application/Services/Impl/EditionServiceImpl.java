package Application.Services.Impl;

import Application.Entities.Edition;
import Application.Services.EditionService;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class EditionServiceImpl implements EditionService {
    EntityManagerFactory emf;

    public EditionServiceImpl(EntityManagerFactory emf){ this.emf =emf; }

    @Override
    public Edition save(Edition edition) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        edition = em.merge(edition);
        em.getTransaction().commit();
        em.close();
        return edition;
    }

    @Override
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Query query = em.createQuery("delete from Edition e where e.id_edition= :id_edition");
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
    public Edition update(Edition edition) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        edition = em.merge(edition);
        em.getTransaction().commit();
        em.close();
        return edition;
    }

    @Override
    public Edition findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Edition edition = em.createQuery("select edition from Edition edition where edition.id_edition = :id_edition", Edition.class)
                .setParameter("id_edition", id)
                .getSingleResult();
        em.close();
        return edition;
    }

    @Override
    public List<Edition> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Edition> editions = em.createQuery("select e from Edition e", Edition.class)
                .getResultList();
        em.close();
        return editions;
    }

    @Override
    public List<Edition> findByIdLib(Long IdLib) {
        EntityManager em = emf.createEntityManager();
        List<Edition> editions = em.createQuery("select e from Edition e where e.id_library = :id_lib", Edition.class)
                .setParameter("id_lib", IdLib)
                .getResultList();
        em.close();
        return editions;
    }

    @Override
    public List<Edition> findByHallNum(int hallNum) {
        EntityManager em = emf.createEntityManager();
        List<Edition> editions = em.createQuery("select e from Edition e where e.hall_num = :hallNum", Edition.class)
                .setParameter("hallNum", hallNum)
                .getResultList();
        em.close();
        return editions;
    }

    @Override
    public List<Edition> findByRackNum(int rackNum) {
        EntityManager em = emf.createEntityManager();
        List<Edition> editions = em.createQuery("select e from Edition e where e.rack_num = :rackNum", Edition.class)
                .setParameter("rackNum", rackNum)
                .getResultList();
        em.close();
        return editions;
    }

    @Override
    public List<Edition> findByShelfNum(int shelfNum) {
        EntityManager em = emf.createEntityManager();
        List<Edition> editions = em.createQuery("select e from Edition e where e.shelf_num = :shelfNum", Edition.class)
                .setParameter("shelfNum", shelfNum)
                .getResultList();
        em.close();
        return editions;
    }

    @Override
    public List<Edition> findByMoreDateAdding(Date dateAdding) {
        EntityManager em = emf.createEntityManager();
        List<Edition> editions = em.createQuery("select e from Edition e where e.date_adding >= :dateAdding", Edition.class)
                .setParameter("dateAdding", dateAdding)
                .getResultList();
        em.close();
        return editions;
    }

    @Override
    public List<Edition> findByLessDateAdding(Date dateAdding) {
        EntityManager em = emf.createEntityManager();
        List<Edition> editions = em.createQuery("select e from Edition e where e.date_adding < :dateAdding ", Edition.class)
                .setParameter("dateAdding", dateAdding)
                .getResultList();
        em.close();
        return editions;
    }

    @Override
    public List<Edition> findByMoreDateRemoving(Date dateRemoving) {
        EntityManager em = emf.createEntityManager();
        List<Edition> editions = em.createQuery("select e from Edition e where e.date_removing >= :dateRemoving", Edition.class)
                .setParameter("dateRemoving", dateRemoving)
                .getResultList();
        em.close();
        return editions;
    }

    @Override
    public List<Edition> findByLessDateRemoving(Date dateRemoving) {
        EntityManager em = emf.createEntityManager();
        List<Edition> editions = em.createQuery("select e from Edition e where e.date_removing < :dateRemoving", Edition.class)
                .setParameter("dateRemoving", dateRemoving)
                .getResultList();
        em.close();
        return editions;
    }

    @Override
    public List<Edition> findByDateAddingAndDateRemoving(Date dateAdding, Date dateRemoving) {
        EntityManager em = emf.createEntityManager();
        List<Edition> editions = em.createQuery("select e from Edition e where e.date_adding >= : dateAdding and e.date_removing <= :dateRemoving", Edition.class)
                .setParameter("dateAdding", dateAdding)
                .setParameter("dateRemoving", dateRemoving)
                .getResultList();
        em.close();
        return editions;
    }

    @Override
    public List<Edition> findByIdLibAndHallNum(Long IdLib, int hallNum) {
        EntityManager em = emf.createEntityManager();
        List<Edition> editions = em.createQuery("select e from Edition e where e.id_library = :id_lib and e.hall_num = :hallNum", Edition.class)
                .setParameter("id_lib", IdLib)
                .setParameter("hallNum", hallNum)
                .getResultList();
        em.close();
        return editions;
    }

    @Override
    public List<Edition> findByIdLibAndRackNum(Long IdLib, int rackNum) {
        EntityManager em = emf.createEntityManager();
        List<Edition> characteristics = em.createQuery("select e from Edition e where e.id_library = :id_lib and e.rack_num = :rackNum", Edition.class)
                .setParameter("id_lib", IdLib)
                .setParameter("rackNum", rackNum)
                .getResultList();
        em.close();
        return characteristics;
    }

    @Override
    public List<Edition> findByIdLibAndShelfNum(Long IdLib, int shelfNum) {
        EntityManager em = emf.createEntityManager();
        List<Edition> characteristics = em.createQuery("select e from Edition e where e.id_library = :id_lib and e.shelf_num = :shelfNum", Edition.class)
                .setParameter("id_lib", IdLib)
                .setParameter("shelfNum", shelfNum)
                .getResultList();
        em.close();
        return characteristics;
    }

    @Override
    public List<Edition> findByHallNumAndRackNum(int hallNum, int rackNum) {
        EntityManager em = emf.createEntityManager();
        List<Edition> characteristics = em.createQuery("select e from Edition e where e.hall_num = :hallNum and e.rack_num = :rackNum", Edition.class)
                .setParameter("hallNum", hallNum)
                .setParameter("rackNum", rackNum)
                .getResultList();
        em.close();
        return characteristics;
    }

    @Override
    public List<Edition> findByHallNumAndShelfNum(int hallNum, int shelfNum) {
        EntityManager em = emf.createEntityManager();
        List<Edition> characteristics = em.createQuery("select e from Edition e where e.hall_num = :hallNum and e.shelf_num = :shelfNum", Edition.class)
                .setParameter("hallNum", hallNum)
                .setParameter("shelfNum", shelfNum)
                .getResultList();
        em.close();
        return characteristics;
    }

    @Override
    public List<Edition> findByRackNumAndShelfNum(int rackNum, int shelfNum) {
        EntityManager em = emf.createEntityManager();
        List<Edition> characteristics = em.createQuery("select e from Edition e where e.rack_num = :rackNum and e.shelf_num = :shelfNum", Edition.class)
                .setParameter("rackNum", rackNum)
                .setParameter("shelfNum", shelfNum)
                .getResultList();
        em.close();
        return characteristics;
    }

    @Override
    public List<Edition> findByIdLibAndHallNumAndRackNum(Long IdLib, int hallNum, int rackNum) {
        EntityManager em = emf.createEntityManager();
        List<Edition> characteristics = em.createQuery("select e from Edition e where e.id_library = :id_lib and e.hall_num = :hallNum and e.rack_num = :rackNum", Edition.class)
                .setParameter("id_lib", IdLib)
                .setParameter("hallNum", hallNum)
                .setParameter("rackNum", rackNum)
                .getResultList();
        em.close();
        return characteristics;
    }

    @Override
    public List<Edition> findByIdLibAndHallNumAndShelfNum(Long IdLib, int hallNum, int shelfNum) {
        EntityManager em = emf.createEntityManager();
        List<Edition> characteristics = em.createQuery("select e from Edition e where e.id_library = :id_lib and e.hall_num = :hallNum and e.shelf_num = :shelfNum", Edition.class)
                .setParameter("id_lib", IdLib)
                .setParameter("hallNum", hallNum)
                .setParameter("shelfNum", shelfNum)
                .getResultList();
        em.close();
        return characteristics;
    }

    @Override
    public List<Edition> findByIdLibAndRackNumAndShelfNum(Long IdLib, int rackNum, int shelfNum) {
        EntityManager em = emf.createEntityManager();
        List<Edition> characteristics = em.createQuery("select e from Edition e where e.id_library = :id_lib and e.rack_num = :rackNum and e.shelf_num = :shelfNum", Edition.class)
                .setParameter("id_lib", IdLib)
                .setParameter("rackNum", rackNum)
                .setParameter("shelfNum", shelfNum)
                .getResultList();
        em.close();
        return characteristics;
    }

    @Override
    public List<Edition> findByHallNumAndRackNumAndShelfNum(int hallNum, int rackNum, int shelfNum) {
        EntityManager em = emf.createEntityManager();
        List<Edition> characteristics = em.createQuery("select e from Edition e where e.hall_num = :hallNum and e.rack_num = :rackNum and e.shelf_num = :shelfNum", Edition.class)
                .setParameter("hallNum", hallNum)
                .setParameter("rackNum", rackNum)
                .setParameter("shelfNum", shelfNum)
                .getResultList();
        em.close();
        return characteristics;
    }

    @Override
    public List<Edition> findByAll(Long IdLib, int hallNum, int rackNum, int shelfNum) {
        EntityManager em = emf.createEntityManager();
        List<Edition> characteristics = em.createQuery("select e from Edition e where e.id_library = :id_lib and e.hall_num = :hallNum and e.rack_num = :rackNum and e.shelf_num = :shelfNum", Edition.class)
                .setParameter("id_lib", IdLib)
                .setParameter("hallNum", hallNum)
                .setParameter("rackNum", rackNum)
                .setParameter("shelfNum", shelfNum)
                .getResultList();
        em.close();
        return characteristics;
    }
}
