package br.com.uniplan.pim.setappapi.repository;

import br.com.uniplan.pim.setappapi.entity.Correspondencia;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CorrespondenciaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Correspondencia> findAll() {
        String hql = "";
        hql = hql.concat("select correspondencia from Correspondencia correspondencia ");
        TypedQuery<Correspondencia> query = entityManager.createQuery(hql, Correspondencia.class);
        return query.getResultList();
    }

    public Correspondencia findById(Long id) {
        String hql = "";
        hql = hql.concat("select correspondencia from Correspondencia correspondencia ");
        hql = hql.concat("where correspondencia.id = :id");
        TypedQuery<Correspondencia> query = entityManager.createQuery(hql, Correspondencia.class);
        query.setParameter("id", id);
        List<Correspondencia> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Transactional
    public Long persist(Correspondencia correspondencia) {
        entityManager.persist(correspondencia);
        return correspondencia.getId();
    }

    @Transactional
    public void merge(Correspondencia correspondencia) {
        entityManager.merge(correspondencia);
    }

    @Transactional
    public void remove(Correspondencia correspondencia) {
        entityManager.remove(correspondencia);
    }

    public Correspondencia findByBlocoApartamento(String bloco, Integer numero) {
        String hql = "";
        hql = hql.concat("select correspondencia from Correspondencia correspondencia where correspondencia.bloco = :bloco and correspondencia.numero = :numero");
        TypedQuery<Correspondencia> query = entityManager.createQuery(hql, Correspondencia.class);
        query.setParameter("bloco", bloco);
        query.setParameter("numero", numero);
        List<Correspondencia> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public Long countByUsuario(Long id) {
        String hql = "";
        hql = hql.concat("select count(correspondencia) from Correspondencia correspondencia where correspondencia.usuario.id = :id");
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

    public Long countByBlocoCorrespondencia(String bloco, Integer numero) {
        String hql = "";
        hql = hql.concat("select count(correspondencia) from Correspondencia correspondencia where correspondencia.bloco = :bloco and correspondencia.numero = :numero ");
        Query query = entityManager.createQuery(hql);
        query.setParameter("bloco", bloco);
        query.setParameter("numero", numero);
        return (Long) query.getSingleResult();
    }

    public long countByBlocoCorrespondenciaExceptWithId(String bloco, Integer numero, Long id) {
        String hql = "";
        hql = hql.concat("select count(correspondencia) from Correspondencia correspondencia where correspondencia.bloco = :bloco and correspondencia.numero = :numero and correspondencia.id <> :id");
        Query query = entityManager.createQuery(hql);
        query.setParameter("bloco", bloco);
        query.setParameter("numero", numero);
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

    public List<Correspondencia> findRemoval() {
        String hql = "";
        hql = hql.concat("select correspondencia from Correspondencia correspondencia ");
        hql = hql.concat("where correspondencia.situacao = 'RETIRADA' ");
        TypedQuery<Correspondencia> query = entityManager.createQuery(hql, Correspondencia.class);
        return query.getResultList();
    }

    public List<Correspondencia> findPendant() {
        String hql = "";
        hql = hql.concat("select correspondencia from Correspondencia correspondencia ");
        hql = hql.concat("where correspondencia.situacao = 'PENDENTE' ");
        TypedQuery<Correspondencia> query = entityManager.createQuery(hql, Correspondencia.class);
        return query.getResultList();
    }

}
