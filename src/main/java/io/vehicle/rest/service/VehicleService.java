package io.vehicle.rest.service;

import java.util.List;

import io.vehicle.rest.data.Make;
import io.vehicle.rest.data.Status;
import io.vehicle.rest.data.Vehicle;

public interface VehicleService {	
	
	Vehicle add (final Vehicle vehicle);
	
	List<Vehicle> getVehicles ();
	
	void modify(final Vehicle vehicle);
	
	void remove(final String vin);
	
	void changeStatus(final String vin, final String status);
	
	Vehicle findByVin(final String vin);
	
	List<Vehicle> searchByMake(final String make);
	
	List<Vehicle> searchByModel(final String model);
	
	List<Vehicle> searchByYear(final String year);
	
	List<Vehicle> searchByPrice(final String price);	

}
