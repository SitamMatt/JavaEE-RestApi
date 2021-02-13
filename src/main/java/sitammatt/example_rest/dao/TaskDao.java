package sitammatt.example_rest.dao;

import sitammatt.example_rest.model.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TaskDao {
    private final EntityManager em;

    public TaskDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Tasks");
        em = emf.createEntityManager();
    }

    public List<Task> getAll(){
        return em.createQuery("from Task").getResultList();
    }

    public Task get(int id){
        return em.find(Task.class, id);
    }

    public void add(Task task){
        em.persist(task);
    }

    public void update(int id, Task task){
//        em.re(task); todo
    }

    public void delete(Task task){
        em.remove(task);
    }
}
