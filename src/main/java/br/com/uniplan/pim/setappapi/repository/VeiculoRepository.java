package br.com.uniplan.pim.setappapi.repository;

import br.com.uniplan.pim.setappapi.entity.Veiculo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class VeiculoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Veiculo> findAll() {
        String hql = "";
        hql = hql.concat("select veiculo from Veiculo veiculo ");
        TypedQuery<Veiculo> query = entityManager.createQuery(hql, Veiculo.class);
        return query.getResultList();
    }

    public Veiculo findById(Long id) {
        String hql = "";
        hql = hql.concat("select veiculo from Veiculo veiculo ");
        hql = hql.concat("where veiculo.id = :id");
        TypedQuery<Veiculo> query = entityManager.createQuery(hql, Veiculo.class);
        query.setParameter("id", id);
        List<Veiculo> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Transactional
    public Long persist(Veiculo veiculo) {
        entityManager.persist(veiculo);
        return veiculo.getId();
    }

    @Transactional
    public void merge(Veiculo veiculo) {
        entityManager.merge(veiculo);
    }

    @Transactional
    public void remove(Veiculo veiculo) {
        entityManager.remove(veiculo);
    }

    public Long countByUsuario(Long id) {
        String hql = "";
        hql = hql.concat("select count(veiculo) from Veiculo veiculo where veiculo.usuario.id = :id");
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

    public Long countByUnidade(Long id) {
        String hql = "";
        hql = hql.concat("select count(veiculo) from Veiculo veiculo where veiculo.unidade.id = :id");
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

    public Long countByUsuarioExceptWithId(Long idUsuario, Long id) {
        String hql = "";
        hql = hql.concat("select count(veiculo) from Veiculo veiculo where veiculo.usuario.id = :idUsuario and veiculo.id <> :id");
        Query query = entityManager.createQuery(hql);
        query.setParameter("idUsuario", idUsuario);
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

    public Long countByUnidadeExceptWithId(Long idUnidade, Long id) {
        String hql = "";
        hql = hql.concat("select count(veiculo) from Veiculo veiculo where veiculo.unidade.id = :idUnidade and veiculo.id <> :id");
        Query query = entityManager.createQuery(hql);
        query.setParameter("idUnidade", idUnidade);
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

}
