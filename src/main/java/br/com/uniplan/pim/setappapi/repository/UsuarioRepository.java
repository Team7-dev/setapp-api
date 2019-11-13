package br.com.uniplan.pim.setappapi.repository;

import br.com.uniplan.pim.setappapi.entity.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Usuario> findAll() {
        String hql = "";
        hql = hql.concat("select usuario from Usuario usuario ");
        TypedQuery<Usuario> query = entityManager.createQuery(hql, Usuario.class);
        return query.getResultList();
    }

    public Usuario findById(Long id) {
        String hql = "";
        hql = hql.concat("select usuario from Usuario usuario ");
        hql = hql.concat("where usuario.id = :id");
        TypedQuery<Usuario> query = entityManager.createQuery(hql, Usuario.class);
        query.setParameter("id", id);
        List<Usuario> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Transactional
    public Long persist(Usuario usuario) {
        entityManager.persist(usuario);
        return usuario.getId();
    }

    @Transactional
    public void merge(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Transactional
    public void remove(Usuario usuario) {
        entityManager.remove(usuario);
    }

    public Long countUsuariosByName(String nome) {
        String hql = "";
        hql = hql.concat("select count(usuario) from Usuario usuario where usuario.nome = :nome");
        Query query = entityManager.createQuery(hql);
        query.setParameter("nome", nome);
        return (Long) query.getSingleResult();
    }

    public Long countUsuariosByNameExceptWithId(String nome, Long id) {
        String hql = "";
        hql = hql.concat("select count(usuario) from Usuario usuario where usuario.nome = :nome and usuario.id <> :id");
        Query query = entityManager.createQuery(hql);
        query.setParameter("nome", nome);
        query.setParameter("id", id);
        return (Long) query.getSingleResult();
    }

    public Long countUsuariosByUsername(String usuario) {
        String hql = "";
        hql = hql.concat("select count(usuario) from Usuario usuario where usuario.usuario = :usuario");
        Query query = entityManager.createQuery(hql);
        query.setParameter("usuario", usuario);
        return (Long) query.getSingleResult();
    }

    public Long countUsuariosByCpf(String cpf) {
        String hql = "";
        hql = hql.concat("select count(usuario) from Usuario usuario where usuario.cpf = :cpf");
        Query query = entityManager.createQuery(hql);
        query.setParameter("cpf", cpf);
        return (Long) query.getSingleResult();
    }
}
