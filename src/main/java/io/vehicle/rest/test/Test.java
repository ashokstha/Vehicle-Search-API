package io.vehicle.rest.test;

import io.vehicle.rest.data.VehicleMap;
import io.vehicle.rest.service.DbUtil;

public class Test {

	public static void main(String[] args) {
		DbUtil util = new DbUtil();
		VehicleMap map = util.deserialize();
		if (map == null)
			map = new VehicleMap();
		util.serialize(map);
		map = util.deserialize();
		System.out.println("ID = " + map.getNextVin());

	}

}
