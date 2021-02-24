package sitammatt.example_rest.controllers;

import sitammatt.example_rest.dto.TaskDto;
import sitammatt.example_rest.dto.TaskPatchDto;
import sitammatt.example_rest.dto.UserDto;
import sitammatt.example_rest.services.UserService;
import sitammatt.example_rest.utils.ResponseHelper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.UUID;

@Stateless
@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    public Response get(){
        var result = userService.getAll();
        return Response.ok(result).build();
    }

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") String id){
        var guid= UUID.fromString(id);
        var result = userService.get(guid);
        if(result == null) {
            return ResponseHelper.notFound();
        }
        return Response.ok(result).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response post(UserDto model, @Context UriInfo uriInfo){

        var result = userService.create(model);

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(result.guid.toString());

        return Response.created(uriBuilder.build()).entity(result).build();
    }

    @PUT
    @Path("{id}")
    public Response put(@PathParam("id") String id, UserDto model){
        var guid= UUID.fromString(id);

        var result = userService.update(guid, model);

        return Response.ok().entity(result).build();
    }

//    @PATCH
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("{id}")
//    public Response patch(@PathParam("id") String id, TaskPatchDto patch) {
//        var guid= UUID.fromString(id);
//
//        var result = userService.patch(guid, patch);
//
//        return Response.ok().entity(result).build();
//    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id){
        var guid= UUID.fromString(id);
        userService.delete(guid);

        return Response.noContent().build();
    }
}
