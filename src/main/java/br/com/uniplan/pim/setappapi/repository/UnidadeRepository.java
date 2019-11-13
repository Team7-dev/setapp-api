package br.com.uniplan.pim.setappapi.repository;

import br.com.uniplan.pim.setappapi.entity.Unidade;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UnidadeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Unidade> findAll() {
        String hql = "";
        hql = hql.concat("select unidade from Unidade unidade ");
        TypedQuery<Unidade> query = entityManager.createQuery(hql, Unidade.class);
        return query.getResultList();
    }

    public Unidade findById(Long id) {
        String hql = "";
        hql = hql.concat("select unidade from Unidade unidade ");
        hql = hql.concat("where unidade.id = :id");
        TypedQuery<Unidade> query = entityManager.createQuery(hql, Unidade.class);
        query.setParameter("id", id);
        List<Unidade> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Transactional
    public Long persist(Unidade unidade) {
        entityManager.persist(unidade);
        return unidade.getId();
    }

    @Transactional
    public void merge(Unidade unidade) {
        entityManager.merge(unidade);
    }

    @Transactional
    public void remove(Unidade unidade) {
        entityManager.remove(unidade);
    }

    public Long countUnidadesByName(String nome) {
        String hql = "";
        hql = hql.concat("select count(unidade) from Unidade unidade where unidade.nome = :nome");
        Query query = entityManager.createQuery(hql);
        query.setParameter("nome", nome);
        return (Long) query.getSingleResult();
    }

    public Long countUnidadesByNameExceptWithId(String nome, Long id) {
        String hql = "";
        hql = hql.concat("select count(unidade) from Unidade unidade where unidade.nome = :nome and unidade.id <> :id");
        Query query = entityManager.createQuery(hql);
        query.setParameter("nome", nome);
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

    public Unidade findByBlocoApartamento(String bloco, Integer numero) {
        String hql = "";
        hql = hql.concat("select unidade from Unidade unidade where unidade.bloco = :bloco and unidade.numero = :numero");
        TypedQuery<Unidade> query = entityManager.createQuery(hql, Unidade.class);
        query.setParameter("bloco", bloco);
        query.setParameter("numero", numero);
        List<Unidade> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
