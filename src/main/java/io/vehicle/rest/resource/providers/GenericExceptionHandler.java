package io.vehicle.rest.resource.providers;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import io.vehicle.rest.data.ErrorInfo;

@Component
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class GenericExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		final ErrorInfo entity = ErrorInfo.builder().errorCode("ERR-001")
				.errorDesc("Exception occurred processing request. Contact admin").build();
		return Response.serverError().entity(entity).build();
	}

}
