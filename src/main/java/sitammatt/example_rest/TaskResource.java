package sitammatt.example_rest;

import sitammatt.example_rest.dao.TaskDao;
import sitammatt.example_rest.model.Task;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.UUID;

@Stateless
@Path("/tasks")
public class TaskResource {

    @Inject
    private TaskDao dao;
//    @PersistenceContext(unitName = "Tasks", type = PersistenceContextType.TRANSACTION)
//    private EntityManager entityManager;

//    @Inject
//    private TaskDao taskDao;

//    @Transactional

    @GET
    public Response get(){
        var tasks = dao.getAll();
        return Response.ok(tasks).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") int id){
        var task = dao.get(id);
        return Response.ok(task).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response post(Task task, @Context UriInfo uriInfo){
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(5));
        task.setId(0);
        dao.add(task);
        return Response.created(uriBuilder.build()).build();
    }

    @PUT
    @Path("{id}")
    public Response put(@PathParam("id") int id, Task task){
        task.setId(0);
        dao.update(id, task);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id){
        var task = dao.get(id);
        dao.delete(task);
        return Response.ok().build();
    }
}
