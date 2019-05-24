package io.vehicle.rest.resource.providers;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import io.vehicle.rest.data.ErrorInfo;
import io.vehicle.rest.exception.VehicleNotFoundException;

@Component
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class VehicleNotFoundExceptionHandler implements ExceptionMapper<VehicleNotFoundException> {

	@Override
	public Response toResponse(VehicleNotFoundException exception) {
		final ErrorInfo entity = ErrorInfo.builder().errorCode("ERR-003").errorDesc(exception.getMessage()).build();
		return Response.status(Status.NOT_FOUND).entity(entity).build();
	}

}
