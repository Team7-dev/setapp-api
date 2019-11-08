package br.com.uniplan.pim.setappapi.repository;

import br.com.uniplan.pim.setappapi.entity.Visitante;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class VisitanteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Visitante> findAll() {
        String hql = "";
        hql = hql.concat("select visitante from Visitante visitante ");
        TypedQuery<Visitante> query = entityManager.createQuery(hql, Visitante.class);
        return query.getResultList();
    }

    public Visitante findById(Long id) {
        String hql = "";
        hql = hql.concat("select visitante from Visitante visitante ");
        hql = hql.concat("where visitante.id = :id");
        TypedQuery<Visitante> query = entityManager.createQuery(hql, Visitante.class);
        query.setParameter("id", id);
        List<Visitante> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Transactional
    public Long persist(Visitante visitante) {
        entityManager.persist(visitante);
        return visitante.getId();
    }

    @Transactional
    public void merge(Visitante visitante) {
        entityManager.merge(visitante);
    }

    @Transactional
    public void remove(Visitante visitante) {
        entityManager.remove(visitante);
    }

    public Long countVisitantesByName(String nome) {
        String hql = "";
        hql = hql.concat("select count(visitante) from Visitante visitante where visitante.nome = :nome");
        Query query = entityManager.createQuery(hql);
        query.setParameter("nome", nome);
        return (Long) query.getSingleResult();
    }

    public Long countVisitantesByNameExceptWithId(String nome, Long id) {
        String hql = "";
        hql = hql.concat("select count(visitante) from Visitante visitante where visitante.nome = :nome and visitante.id <> :id");
        Query query = entityManager.createQuery(hql);
        query.setParameter("nome", nome);
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

}
