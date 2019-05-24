package io.vehicle.rest.util;

import io.vehicle.rest.data.Make;
import io.vehicle.rest.data.Status;

public class StringToEnum {
	
	public static Status convertStatus(String status) {
		try {
			return Status.valueOf(status);
		}catch(Exception e) {
			return null;
		}
	}
	
	public static Make convertMake(String make) {
		try {
			return Make.valueOf(make);
		}catch(Exception e) {
			return null;
		}
	}
	
//	public <T extends Enum<T>> T convert(T tEnum, String status) {
//		try {
//			return T.valueOf(Class<tEnum>, status);
//		}catch(Exception e) {
//			return null;
//		}
//	}

}
