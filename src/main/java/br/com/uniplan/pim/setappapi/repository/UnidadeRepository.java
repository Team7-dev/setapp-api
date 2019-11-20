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

    public Unidade findByBlocoApartamento(String bloco, Integer numero) {
        String hql = "";
        hql = hql.concat("select unidade from Unidade unidade where unidade.bloco = :bloco and unidade.numero = :numero");
        TypedQuery<Unidade> query = entityManager.createQuery(hql, Unidade.class);
        query.setParameter("bloco", bloco);
        query.setParameter("numero", numero);
        List<Unidade> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public Long countByUsuario(Long id) {
        String hql = "";
        hql = hql.concat("select count(unidade) from Unidade unidade where unidade.usuario.id = :id");
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

    public Long countByBlocoUnidade(String bloco, Integer numero) {
        String hql = "";
        hql = hql.concat("select count(unidade) from Unidade unidade where unidade.bloco = :bloco and unidade.numero = :numero ");
        Query query = entityManager.createQuery(hql);
        query.setParameter("bloco", bloco);
        query.setParameter("numero", numero);
        return (Long) query.getSingleResult();
    }

    public long countByBlocoUnidadeExceptWithId(String bloco, Integer numero, Long id) {
        String hql = "";
        hql = hql.concat("select count(unidade) from Unidade unidade where unidade.bloco = :bloco and unidade.numero = :numero and unidade.id <> :id");
        Query query = entityManager.createQuery(hql);
        query.setParameter("bloco", bloco);
        query.setParameter("numero", numero);
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

    public List<Unidade> findUnoccupied() {
        String hql = "";
        hql = hql.concat("select unidade from Unidade unidade ");
        hql = hql.concat("where unidade.situacao = 'VAGO' ");
        TypedQuery<Unidade> query = entityManager.createQuery(hql, Unidade.class);
        return query.getResultList();
    }

    public List<Unidade> findOccupied() {
        String hql = "";
        hql = hql.concat("select unidade from Unidade unidade ");
        hql = hql.concat("where unidade.situacao = 'OCUPADO' ");
        TypedQuery<Unidade> query = entityManager.createQuery(hql, Unidade.class);
        return query.getResultList();
    }

}
