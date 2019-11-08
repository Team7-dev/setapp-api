package br.com.uniplan.pim.setappapi.repository;

import br.com.uniplan.pim.setappapi.entity.Job;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JobRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Job> findAll() {
        String hql = "";
        hql = hql.concat("select job from Job job ");
//        hql = hql.concat("left join job.parentJob ");
//        hql = hql.concat("left join job.tasks ");
        TypedQuery<Job> query = entityManager.createQuery(hql, Job.class);
        return query.getResultList();
    }

    public Job findById(Long id) {
        String hql = "";
        hql = hql.concat("select job from Job job ");
        hql = hql.concat("left join fetch job.parentJob ");
        hql = hql.concat("left join fetch job.tasks ");
        hql = hql.concat("where job.id = :id");
        TypedQuery<Job> query = entityManager.createQuery(hql, Job.class);
        query.setParameter("id", id);
        List<Job> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public Long persist(Job job) {
        entityManager.persist(job);
        return job.getId();
    }

    public void merge(Job job) {
        entityManager.merge(job);
    }

    public void remove(Job job) {
        entityManager.remove(job);
    }

    public Long countJobsByName(String name) {
        String hql = "";
        hql = hql.concat("select count(job) from Job job where job.name = :name");
        Query query = entityManager.createQuery(hql);
        query.setParameter("name", name);
        return (Long) query.getSingleResult();
    }

    public Long countJobsByNameExceptWithId(String name, Long id) {
        String hql = "";
        hql = hql.concat("select count(job) from Job job where job.name = :name and job.id <> :id");
        Query query = entityManager.createQuery(hql);
        query.setParameter("name", name);
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

}