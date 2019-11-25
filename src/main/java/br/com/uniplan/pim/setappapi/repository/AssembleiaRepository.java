package br.com.uniplan.pim.setappapi.repository;

import br.com.uniplan.pim.setappapi.entity.Assembleia;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class AssembleiaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Assembleia> findAll() {
        String hql = "";
        hql = hql.concat("select assembleia from Assembleia assembleia ");
        TypedQuery<Assembleia> query = entityManager.createQuery(hql, Assembleia.class);
        return query.getResultList();
    }

    public Assembleia findById(Long id) {
        String hql = "";
        hql = hql.concat("select assembleia from Assembleia assembleia ");
        hql = hql.concat("where assembleia.id = :id");
        TypedQuery<Assembleia> query = entityManager.createQuery(hql, Assembleia.class);
        query.setParameter("id", id);
        List<Assembleia> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Transactional
    public Long persist(Assembleia assembleia) {
        entityManager.persist(assembleia);
        return assembleia.getId();
    }

    @Transactional
    public void merge(Assembleia assembleia) {
        entityManager.merge(assembleia);
    }

    @Transactional
    public void remove(Assembleia assembleia) {
        entityManager.remove(assembleia);
    }

    public List<Assembleia> findPendant() {
        String hql = "";
        hql = hql.concat("select assembleia from Assembleia assembleia ");
        hql = hql.concat("where assembleia.situacao = 'PENDENTE' ");
        TypedQuery<Assembleia> query = entityManager.createQuery(hql, Assembleia.class);
        return query.getResultList();
    }

    public List<Assembleia> findDone() {
        String hql = "";
        hql = hql.concat("select assembleia from Assembleia assembleia ");
        hql = hql.concat("where assembleia.situacao = 'CONCLUIDA' ");
        TypedQuery<Assembleia> query = entityManager.createQuery(hql, Assembleia.class);
        return query.getResultList();
    }

}
