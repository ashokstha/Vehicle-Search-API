package io.vehicle.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.vehicle.rest.data.Make;
import io.vehicle.rest.data.Status;
import io.vehicle.rest.data.Vehicle;
import io.vehicle.rest.data.VehicleMap;
import io.vehicle.rest.exception.ValidationException;
import io.vehicle.rest.exception.VehicleNotFoundException;
import io.vehicle.rest.util.StringToEnum;

@Service
public class VehicleServiceSerializeImpl implements VehicleService {

	@Autowired
	private DbUtil util;

	@Override
	public Vehicle add(final Vehicle vehicle) {
		final VehicleMap vehicleMap = this.getVehicleMap();
		final String vin = vehicleMap.getNextVin();
		vehicle.setVin(vin);

		vehicleMap.save(vehicle);
		util.serialize(vehicleMap);

		return vehicle;
	}

	private VehicleMap getVehicleMap() {

		VehicleMap vehicleMap = util.deserialize();
		if (vehicleMap == null) {
			vehicleMap = new VehicleMap();
		}

		return vehicleMap;
	}

	@Override
	public void modify(final String vin, Vehicle vehicle) {
		vehicle.setVin(vin);
		validateVehicle(vehicle);
		final VehicleMap vehicleMap = this.getVehicleMap();
		if (vehicleMap.find(vehicle.getVin()) == null) {
			throw new VehicleNotFoundException("Vehicle not found!");
		}

		if (vehicle.getStatus() != Status.PENDING) {
			throw new ValidationException("Vehicle status is not in PENDING!");
		}

		vehicleMap.save(vehicle);
		util.serialize(vehicleMap);
	}

	private void validateVehicle(final Vehicle vehicle) {
		if (vehicle == null || vehicle.getVin() == null) {
			throw new ValidationException("Invalid Vehicle info for persistence!");
		}

	}

	@Override
	public void remove(String vin) {
		final VehicleMap vehicleMap = this.getVehicleMap();
		Vehicle vehicle = vehicleMap.find(vin);

		this.validateVehicle(vehicle);

		if (vehicle.getStatus() != Status.PENDING) {
			throw new ValidationException("Vehicle status is not in PENDING!");
		}

		vehicleMap.remove(vin);
		util.serialize(vehicleMap);
	}

	public List<Status> getNextStatus(Status status) {
		List<Status> lst = new ArrayList<Status>();
		switch (status) {
		case PENDING:
			lst.add(Status.ACTIVE);
			break;
		case ACTIVE:
			lst.add(Status.CANCELLED);
			lst.add(Status.PENDING);
			lst.add(Status.SOLD);
			break;
		case SOLD:
			break;
		case CANCELLED:
			break;
		}
		return lst;
	}

	public boolean isValidNextStatus(Status oldStatus, Status newStatus) {
		List<Status> statuses = this.getNextStatus(oldStatus);
		for (Status status : statuses) {
			if (status == newStatus) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void changeStatus(final String vin, final String statusValue) {
		Status status = StringToEnum.convertStatus(statusValue);
		if (status == null) {
			throw new ValidationException("Vehicle status is not Valid!");
		}

		final VehicleMap vehicleMap = this.getVehicleMap();
		Vehicle vehicle = vehicleMap.find(vin);
		if (vehicle == null) {
			throw new VehicleNotFoundException("Vehicle not found!");
		}

		// check if next status is valid for current status
		if (!this.isValidNextStatus(vehicle.getStatus(), status)) {
			throw new ValidationException("New Vehicle status is not Valid!");
		}
		
		vehicle.setStatus(status);
		vehicleMap.save(vehicle);

		util.serialize(vehicleMap);

	}

	@Override
	public Vehicle findByVin(final String vin) {
		VehicleMap vehicleMap = this.getVehicleMap();
		Vehicle vehicle = vehicleMap.find(vin);
		if (vehicle == null) {
			throw new VehicleNotFoundException("Vehicle not found!");
		}
		return vehicle;
	}

	@Override
	public List<Vehicle> searchByMake(final String makeVal) {
		final Make make = StringToEnum.convertMake(makeVal);
		if (make == null) {
			throw new ValidationException("Vehicle make is not Valid!");
		}

		final List<Vehicle> searchResult = new ArrayList<>();
		this.getVehicleMap().getAll().forEach(e -> {
			if (e.getMake() == make) {
				searchResult.add(e);
			}
		});

		return searchResult;
	}

	@Override
	public List<Vehicle> searchByModel(String model) {
		final List<Vehicle> searchResult = new ArrayList<>();
		this.getVehicleMap().getAll().forEach(e -> {
			if (e.getModel().equals(model)) {
				searchResult.add(e);
			}
		});

		return searchResult;
	}

	@Override
	public List<Vehicle> searchByYear(String year) {
		final List<Vehicle> searchResult = new ArrayList<>();
		this.getVehicleMap().getAll().forEach(e -> {
			System.out.println("year: "+e.getModelYear() + " new year" + year);
			if (e.getModelYear().equals(year)) {
				searchResult.add(e);
				System.out.println("Added to list");
			}
		});

		return searchResult;
	}

	@Override
	public List<Vehicle> searchByPrice(String priceVal) {
		final float price = Float.parseFloat(priceVal);
		final List<Vehicle> searchResult = new ArrayList<>();
		this.getVehicleMap().getAll().forEach(e -> {
			if (e.getPrice() == price) {
				searchResult.add(e);
			}
		});

		return searchResult;
	}

	@Override
	public List<Vehicle> getVehicles() {
		final List<Vehicle> searchResult = new ArrayList<>();
		this.getVehicleMap().getAll().forEach(e -> searchResult.add(e));

		return searchResult;
	}

}
