package io.vehicle.rest.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.vehicle.rest.data.Vehicle;
import io.vehicle.rest.service.VehicleService;

@Service
@Path("/vehicle")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VehicleResource {

	@Autowired
	VehicleService vs;

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Vehicle> getVehicles() {
		List<Vehicle> result = vs.getVehicles();
		return result;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces(MediaType.APPLICATION_XML)
	public Response create(final Vehicle vehicle) {
		Vehicle resultObj = vs.add(vehicle);
		return Response.ok().entity(resultObj).build();
	}
	
	@GET
	@Path("/{vin}")
	public Response findByVin(@PathParam("vin") final String vin) {
		Vehicle vehicle = vs.findByVin(vin);		
		return Response.ok().entity(vehicle).build();
		
	}
	
	@PUT
	@Path("/{vin}")
	public Response modify(@PathParam("vin") final String vin, Vehicle vehicle) {
		vs.modify(vin, vehicle);
		return Response.noContent().build();
	}
	
	@DELETE
	@Path("/{vin}")
	public Response delete(@PathParam("vin") final String vin) {
		vs.remove(vin);
		return Response.noContent().build();
	}
	
	@PUT
	@Path("/{vin}/status/{status}")
	public Response changeStatus(@PathParam("vin") final String vin, @PathParam("status")final String status) {
		vs.changeStatus(vin, status);
		return Response.noContent().build();
	}
	
	@GET
	@Path("/search")
	public List<Vehicle>  searchByMake(@QueryParam("make") final String make,@QueryParam("model") final String model,
			@QueryParam("year") final String year, @QueryParam("price") final String price) {
		List<Vehicle> vehicles = new ArrayList<>();
		
		if(make!=null)
			vehicles.addAll(vs.searchByMake(make));
		
		if(model!=null)
			vehicles.addAll(vs.searchByModel(model));
		
		if(year!=null)
			vehicles.addAll(vs.searchByYear(year));
		
		if(price!=null)
			vehicles.addAll(vs.searchByPrice(price));
		
		
		return vehicles;
	}

}
