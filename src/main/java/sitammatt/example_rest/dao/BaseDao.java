package sitammatt.example_rest.dao;

import sitammatt.example_rest.model.BaseEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public abstract class BaseDao<T extends BaseEntity> {

    protected abstract EntityManager getEntityManager();

    protected Class<T> entityClass;

    public List<T> getAll(){
        var queryName = entityClass.getSimpleName() + ".findAll";
        return getEntityManager().createNamedQuery(queryName, entityClass).getResultList();
    }

    public T get(UUID guid){
        return getEntityManager().find(entityClass, guid);
    }

    public void add(T entity){
        getEntityManager().persist(entity);
    }

    public void update(T entity){
        getEntityManager().merge(entity);
    }

    public void delete(T entity){
        getEntityManager().remove(entity);
    }

    public void commit(){
        getEntityManager().flush();
    }
}
