package io.vehicle.rest.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.vehicle.rest.util.RandomString;

public class VehicleMap implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, Vehicle> Vehicles = new HashMap<String, Vehicle>();

	public String getNextVin() {
		return RandomString.getALphaNum(17);
	}

	public void save(final Vehicle vehicle) {
		Vehicles.put(vehicle.getVin(), vehicle);
	}

	public Vehicle find(final String vin) {
		return Vehicles.get(vin);
	}

	public void remove(final String vin) {
		Vehicles.remove(vin);
	}

	public List<Vehicle> getAll() {
		return new ArrayList<Vehicle>(Vehicles.values());
	}
}
