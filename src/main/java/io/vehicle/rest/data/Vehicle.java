package io.vehicle.rest.data;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@XmlRootElement
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Vehicle implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String vin;	
	private Make make;
	private String model;
	private String modelYear;
	private String description;
	private Status status;
	private Date soldDate;
	private Date createdDate;
	private float price;
	
	

}
