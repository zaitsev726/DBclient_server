package Application.Services.Impl;

import Application.Entities.Rule;
import Application.Services.RuleService;

import javax.persistence.*;
import java.util.List;

public class RuleServiceImpl implements RuleService {

    EntityManagerFactory emf;
    public RuleServiceImpl(){
        emf = Persistence.createEntityManagerFactory("model");
    }

    @Override
    public void save(Rule rule) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(rule);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Query query = em.createQuery("delete from Rule r where r.id_rule= :id");
            query.setParameter("id", id);
            query.executeUpdate();
            em.getTransaction().commit();
        }catch (RollbackException e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        em.close();
    }

    @Override
    public Rule update(Rule rule) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            rule = em.merge(rule);
            em.getTransaction().commit();
        }catch (RollbackException e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        em.close();
        return rule;
    }

    @Override
    public Rule findById(Long id) {
        EntityManager em = emf.createEntityManager();
        Rule rule = em.createQuery("select r from Rule r where r.id_rule = :id", Rule.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return rule;
    }

    @Override
    public List<Rule> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Rule> rules = em.createQuery("select r from Rule r", Rule.class)
                .getResultList();
        em.close();
        return rules;
    }

    @Override
    public List<Rule> findByIdEdition(long id_edition) {
        EntityManager em = emf.createEntityManager();
        List<Rule> rules = em.createQuery("select r from Rule r where r.id_edition = :id_edition", Rule.class)
                .setParameter("id_edition", id_edition)
                .getResultList();
        em.close();
        return rules;
    }
}
