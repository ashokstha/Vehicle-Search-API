package io.vehicle.rest.resource.providers;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import io.vehicle.rest.data.ErrorInfo;
import io.vehicle.rest.exception.ValidationException;

@Component
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ValidationExceptionHandler implements ExceptionMapper<ValidationException> {

	@Override
	public Response toResponse(ValidationException exception) {
		final ErrorInfo entity = ErrorInfo.builder().errorCode("ERR-002").errorDesc(exception.getMessage()).build();
		return Response.status(510).type(MediaType.APPLICATION_JSON).entity(entity).build();
	}

}
