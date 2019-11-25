package br.com.uniplan.pim.setappapi.repository;

import br.com.uniplan.pim.setappapi.entity.Ocorrencia;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OcorrenciaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Ocorrencia> findAll() {
        String hql = "";
        hql = hql.concat("select ocorrencia from Ocorrencia ocorrencia ");
        TypedQuery<Ocorrencia> query = entityManager.createQuery(hql, Ocorrencia.class);
        return query.getResultList();
    }

    public Ocorrencia findById(Long id) {
        String hql = "";
        hql = hql.concat("select ocorrencia from Ocorrencia ocorrencia ");
        hql = hql.concat("where ocorrencia.id = :id");
        TypedQuery<Ocorrencia> query = entityManager.createQuery(hql, Ocorrencia.class);
        query.setParameter("id", id);
        List<Ocorrencia> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Transactional
    public Long persist(Ocorrencia ocorrencia) {
        entityManager.persist(ocorrencia);
        return ocorrencia.getId();
    }

    @Transactional
    public void merge(Ocorrencia ocorrencia) {
        entityManager.merge(ocorrencia);
    }

    @Transactional
    public void remove(Ocorrencia ocorrencia) {
        entityManager.remove(ocorrencia);
    }

}
