package io.vehicle.rest.service;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.stereotype.Component;

import io.vehicle.rest.data.VehicleMap;

@Component
public class DbUtil {

	private static final String FILE_PATH = "/Users/ashok/Downloads/eclipse-workspace/shashi/db/vehicledb.txt";

	public void serialize(final VehicleMap vehicleMap) {
		if (System.getProperty("TESTMODE") != null) {
			throw new RuntimeException("Cannot access resouce");
		}
		ObjectOutputStream ObjOut = null;
		try {
			ObjOut = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
			ObjOut.writeObject(vehicleMap);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ObjOut);
		}
	}

	private void close(final Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public VehicleMap deserialize() {
		if (System.getProperty("TESTMODE") != null) {
			throw new RuntimeException("Cannot access resouce");
		}
		VehicleMap result = null;
		ObjectInputStream ObjIn = null;
		try {
			ObjIn = new ObjectInputStream(new FileInputStream(FILE_PATH));
			result = (VehicleMap) ObjIn.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ObjIn);
		}
		return result;
	}

}
