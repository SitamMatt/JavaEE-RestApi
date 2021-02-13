package sitammatt.example_rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import sitammatt.example_rest.Utils.ResponseHelper;
import sitammatt.example_rest.dao.TaskDao;
import sitammatt.example_rest.dto.ErrorDto;
import sitammatt.example_rest.dto.TaskDto;
import sitammatt.example_rest.model.Task;
import sitammatt.example_rest.services.TaskService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

@Stateless
@Path("/tasks")
public class TaskResource {

    @Inject
    private TaskDao dao;

    @Inject
    private TaskService taskService;
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
        if(task == null) {
            return ResponseHelper.notFound();
        }
        return Response.ok(task).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response post(TaskDto task, @Context UriInfo uriInfo){

        var result = taskService.create(task);

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(result.id));

        return Response.created(uriBuilder.build()).entity(result).build();
    }

    @PUT
    @Path("{id}")
    public Response put(@PathParam("id") int id, Task task){
        task.setId(0);
        dao.update(id, task);
        return Response.ok().build();
    }

    @PATCH
    @Path("{id}")
    public Response patch(@PathParam("id") int id, InputStream entity) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(entity, Map.class);

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
