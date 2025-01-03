package br.elwgomes.infra.exception.handler;

import br.elwgomes.core.exception.BadRequestException;
import br.elwgomes.core.exception.NotFoundException;
import br.elwgomes.infra.exception.response.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        exception.printStackTrace();

        if (exception instanceof NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .type("application/json")
                    .entity(new ErrorResponse("NOT_FOUND", LocalDateTime.now(), exception.getMessage()))
                    .build();
        }

        if (exception instanceof IllegalArgumentException || exception instanceof BadRequestException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .type("application/json")
                    .entity(new ErrorResponse("BAD_REQUEST", LocalDateTime.now(), exception.getMessage()))
                    .build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .type("application/json")
                .entity(new ErrorResponse("INTERNAL_SERVER_ERROR", LocalDateTime.now(), "Ocorreu um erro inesperado no servidor."))
                .build();
    }
}