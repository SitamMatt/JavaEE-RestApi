package sitammatt.example_rest;

import sitammatt.example_rest.model.Person;
import sitammatt.example_rest.model.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/tasks")
public class TaskResource {
//    @PersistenceContext
//    private EntityManager entityManager;

    @GET
    public Response get(){
        var task = new Task();
        task.setTitle("Hello Task");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Books");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        em.createNamedQuery("Person.getAll", Person.class).getResultList();

        return Response.ok(task).build();
    }
}
