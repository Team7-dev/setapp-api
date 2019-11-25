package br.com.uniplan.pim.setappapi.repository;

import br.com.uniplan.pim.setappapi.entity.Reserva;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ReservaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Reserva> findAll() {
        String hql = "";
        hql = hql.concat("select reserva from Reserva reserva ");
        TypedQuery<Reserva> query = entityManager.createQuery(hql, Reserva.class);
        return query.getResultList();
    }

    public Reserva findById(Long id) {
        String hql = "";
        hql = hql.concat("select reserva from Reserva reserva ");
        hql = hql.concat("where reserva.id = :id");
        TypedQuery<Reserva> query = entityManager.createQuery(hql, Reserva.class);
        query.setParameter("id", id);
        List<Reserva> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Transactional
    public Long persist(Reserva reserva) {
        entityManager.persist(reserva);
        return reserva.getId();
    }

    @Transactional
    public void merge(Reserva reserva) {
        entityManager.merge(reserva);
    }

    @Transactional
    public void remove(Reserva reserva) {
        entityManager.remove(reserva);
    }

}
