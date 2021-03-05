package sitammatt.example_rest.dao;

import sitammatt.example_rest.model.BaseEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public abstract class BaseDao<T extends BaseEntity> {

    protected EntityManager em;
    protected Class<T> entityClass;

    public BaseDao(EntityManager em, Class<T> entityClass) {
        this.em = em;
        this.entityClass = entityClass;
    }

    public List<T> getAll(){
        var queryName = entityClass.getSimpleName() + ".findAll";
        return em.createNamedQuery(queryName, entityClass).getResultList();
    }

    public T get(UUID guid){
        return em.find(entityClass, guid);
    }

    public void add(T entity){
        em.persist(entity);
    }

    public void update(T entity){
        em.merge(entity);
    }

    public void delete(T entity){
        em.remove(entity);
    }

    public void commit(){
        em.flush();
    }
}
