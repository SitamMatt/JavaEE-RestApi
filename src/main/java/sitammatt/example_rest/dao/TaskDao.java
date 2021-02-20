package sitammatt.example_rest.dao;

import sitammatt.example_rest.model.Task;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

public class TaskDao {

    @PersistenceContext(unitName = "Tasks", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    public TaskDao() {
    }

    public List<Task> getAll(){
        return em.createQuery("from Task").getResultList();
    }

    public Task get(UUID guid){
        return em.find(Task.class, guid);
    }

    public void add(Task task){
        em.persist(task);
    }

    public void update(Task task){
        em.merge(task);
    }

    public void delete(Task task){
        em.remove(task);
    }
}
