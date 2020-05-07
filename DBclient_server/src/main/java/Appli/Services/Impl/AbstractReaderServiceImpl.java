package Appli.Services.Impl;

import Appli.Entities.Types.AbstractReader;
import Appli.Services.AbstractReaderService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.*;

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
        System.out.println("******************************************************" + reader.getId_reader());
        switch (reader.getType()) {
            case ("pensioner"):
                deleteFromPensioner(reader);
                break;
            case ("schoolkid"):
                deleteFromSchoolkid(reader);
                break;
            case ("scientist"):
                deleteFromScientist(reader);
                break;
            case ("student"):
                deleteFromStudent(reader);
                break;
            case ("teacher"):
                deleteFromTeacher(reader);
                break;
            case ("worker"):
                deleteFromWorker(reader);
                break;
            default:
                break;
        }
    }

    private void deleteFromPensioner(AbstractReader reader){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        System.out.println("******************************************************" + reader.getId_reader());
        Query query = em.createQuery("delete from Pensioner p where p.id_reader = :r_id_reader");
        query.setParameter("r_id_reader", reader.getId_reader());
        query.executeUpdate();
        System.out.println("******************************************************" + reader.getId_reader());
        em.getTransaction().commit();
        em.close();
    }
    private void deleteFromSchoolkid(AbstractReader reader){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("delete from Schoolkid s where s.id_reader = :r_id_reader");
        query.setParameter("r_id_reader", reader.getId_reader());
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    private void deleteFromScientist(AbstractReader reader){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("delete from Scientist s where s.id_reader = :r_id_reader");
        query.setParameter("r_id_reader", reader.getId_reader());
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    private void deleteFromStudent(AbstractReader reader){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("delete from Student s where s.id_reader = :r_id_reader");
        query.setParameter("r_id_reader", reader.getId_reader());
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    private void deleteFromTeacher(AbstractReader reader){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("delete from Teacher t where t.id_reader = :r_id_reader");
        query.setParameter("r_id_reader", reader.getId_reader());
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    private void deleteFromWorker(AbstractReader reader){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("delete from Worker w where w.id_reader = :r_id_reader");
        query.setParameter("r_id_reader", reader.getId_reader());
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
