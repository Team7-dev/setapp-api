package br.com.uniplan.pim.setappapi.repository;

import br.com.uniplan.pim.setappapi.entity.Perfil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PerfilRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Perfil findById(Long id) {
        String hql = "";
        hql = hql.concat("select perfil from Perfil perfil ");
        hql = hql.concat("where perfil.id = :id");
        TypedQuery<Perfil> query = entityManager.createQuery(hql, Perfil.class);
        query.setParameter("id", id);
        List<Perfil> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public Perfil findByPerfil(String perfil) {
        String hql = "";
        hql = hql.concat("select perfil from Perfil perfil ");
        hql = hql.concat("where perfil.perfil = :perfil");
        TypedQuery<Perfil> query = entityManager.createQuery(hql, Perfil.class);
        query.setParameter("perfil", perfil);
        List<Perfil> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

}
