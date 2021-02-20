package sitammatt.example_rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import sitammatt.example_rest.Utils.ResponseHelper;
import sitammatt.example_rest.dto.TaskDto;
import sitammatt.example_rest.model.Task;
import sitammatt.example_rest.services.TaskService;

import javax.ejb.Stateless;
import javax.inject.Inject;
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
    private TaskService taskService;

    @GET
    public Response get(){
        var tasks = taskService.getAll();
        return Response.ok(tasks).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") String id){
        var guid= UUID.fromString(id);
        var task = taskService.get(guid);
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
        uriBuilder.path(result.guid.toString());

        return Response.created(uriBuilder.build()).entity(result).build();
    }

    @PUT
    @Path("{id}")
    public Response put(@PathParam("id") String id, TaskDto task){
        var guid= UUID.fromString(id);

        taskService.update(guid, task);
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
    public Response delete(@PathParam("id") String guid){
        var parsedGuid= UUID.fromString(guid);
        taskService.delete(parsedGuid);

        return Response.noContent().build();
    }
}
