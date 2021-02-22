package sitammatt.example_rest;

import sitammatt.example_rest.dto.TaskPatchDto;
import sitammatt.example_rest.utils.ResponseHelper;
import sitammatt.example_rest.dto.TaskDto;
import sitammatt.example_rest.services.TaskService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
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

        var result = taskService.update(guid, task);

        return Response.ok().entity(result).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response patch(@PathParam("id") String id, TaskPatchDto patch) {
        var guid= UUID.fromString(id);

        var result = taskService.patch(guid, patch);

        return Response.ok().entity(result).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id){
        var guid= UUID.fromString(id);
        taskService.delete(guid);

        return Response.noContent().build();
    }
}
