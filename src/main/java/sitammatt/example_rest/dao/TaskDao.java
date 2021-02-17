package sitammatt.example_rest.dao;

import sitammatt.example_rest.model.Task;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

public class TaskDao {

    @PersistenceContext(unitName = "Tasks", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

//    private final EntityManager em;

    public TaskDao() {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Tasks");
//        em = emf.createEntityManager();
    }

    public List<Task> getAll(){
        return em.createQuery("from Task").getResultList();
    }

    public Task get(UUID guid){
        try{
            var query = em.createQuery("FROM Task WHERE guid = ?1", Task.class);
            query.setParameter(1, guid);
            return query.getSingleResult();
        }catch(NoResultException ex){
            return null;
        }
    }

    public void add(Task task){
        em.persist(task);
    }

    public void update(Task task){
        em.merge(task);
//        em.re(task); todo
    }

    public void delete(Task task){
        em.remove(task);
    }
}
