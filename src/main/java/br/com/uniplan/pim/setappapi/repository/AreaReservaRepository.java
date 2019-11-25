package br.com.uniplan.pim.setappapi.repository;

import br.com.uniplan.pim.setappapi.entity.AreaReserva;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AreaReservaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public AreaReserva findById(Long id) {
        String hql = "";
        hql = hql.concat("select areaReserva from AreaReserva areaReserva ");
        hql = hql.concat("where areaReserva.id = :id");
        TypedQuery<AreaReserva> query = entityManager.createQuery(hql, AreaReserva.class);
        query.setParameter("id", id);
        List<AreaReserva> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public AreaReserva findByArea(String areaReserva) {
        String hql = "";
        hql = hql.concat("select areaReserva from AreaReserva areaReserva ");
        hql = hql.concat("where areaReserva.areaReserva = :areaReserva");
        TypedQuery<AreaReserva> query = entityManager.createQuery(hql, AreaReserva.class);
        query.setParameter("areaReserva", areaReserva);
        List<AreaReserva> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

}
