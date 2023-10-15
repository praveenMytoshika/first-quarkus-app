package com.identity.exception;

import com.identity.enums.Status;
import com.identity.model.response.generic.BaseResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GenericExceptionHandler implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable ex) {
        if (ex instanceof NotFoundException) return handleNotFoundException((NotFoundException) ex);
        else if (ex instanceof BadRequestException) return handleBadRequestException((BadRequestException) ex);
        else if (ex instanceof UnauthorizedException) return handleUnAuthorizedException((UnauthorizedException) ex);
        else return handleDefaultException(ex);
    }

    private Response handleNotFoundException(NotFoundException ex) {
        BaseResponse<Object> response = BaseResponse.builder()
                .status(Status.FAILURE)
                .errorMsg(ex.getMessage())
                .error("Not Found")
                .build();

        return Response.status(Response.Status.NOT_FOUND).entity(response).build();
    }

    private Response handleBadRequestException(BadRequestException ex) {
        BaseResponse<Object> response = BaseResponse.builder()
                .status(Status.FAILURE)
                .errorMsg(ex.getMessage())
                .error("Bad Request")
                .build();

        return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
    }

    private Response handleUnAuthorizedException(UnauthorizedException ex) {
        BaseResponse<Object> response = BaseResponse.builder()
                .status(Status.FAILURE)
                .errorMsg(ex.getMessage())
                .error("Unauthorized")
                .build();

        return Response.status(Response.Status.UNAUTHORIZED).entity(response).build();
    }

    private Response handleDefaultException(Throwable ex) {
        BaseResponse<Object> response = BaseResponse.builder()
                .status(Status.FAILURE)
                .errorMsg("An error occurred: " + ex.getMessage())
                .error("Internal Server Error")
                .build();

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
    }
}
