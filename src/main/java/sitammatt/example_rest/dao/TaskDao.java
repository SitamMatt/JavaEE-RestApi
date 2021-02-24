package sitammatt.example_rest.dao;

import sitammatt.example_rest.model.Task;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

public class TaskDao extends BaseDao<Task>{

    @PersistenceContext(unitName = "Tasks", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TaskDao() {
        entityClass = Task.class;
    }
}
