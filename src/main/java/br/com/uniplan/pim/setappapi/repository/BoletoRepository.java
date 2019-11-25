package br.com.uniplan.pim.setappapi.repository;

import br.com.uniplan.pim.setappapi.entity.Boleto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class BoletoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Boleto> findAll() {
        String hql = "";
        hql = hql.concat("select boleto from Boleto boleto ");
        TypedQuery<Boleto> query = entityManager.createQuery(hql, Boleto.class);
        return query.getResultList();
    }

    public Boleto findById(Long id) {
        String hql = "";
        hql = hql.concat("select boleto from Boleto boleto ");
        hql = hql.concat("where boleto.id = :id");
        TypedQuery<Boleto> query = entityManager.createQuery(hql, Boleto.class);
        query.setParameter("id", id);
        List<Boleto> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Transactional
    public Long persist(Boleto boleto) {
        entityManager.persist(boleto);
        return boleto.getId();
    }

    @Transactional
    public void merge(Boleto boleto) {
        entityManager.merge(boleto);
    }

    @Transactional
    public void remove(Boleto boleto) {
        entityManager.remove(boleto);
    }

}
