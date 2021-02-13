package sitammatt.example_rest.Utils;

import sitammatt.example_rest.dto.ErrorDto;

import javax.ws.rs.core.Response;

public class ResponseHelper {
    public static Response notFound(){
        var error = new ErrorDto();
        error.Message = "Resource not found";
        error.Status = Response.Status.NOT_FOUND.getStatusCode();
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
}
