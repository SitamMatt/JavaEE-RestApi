package sitammatt.example_rest;

import sitammatt.example_rest.model.Task;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/tasks")
public class TaskResource {
    @GET
    public Response get(){
        var task = new Task();
        task.setTitle("Hello Task");
        return Response.ok(task).build();
    }
}
