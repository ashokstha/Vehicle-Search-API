package io.vehicle.rest.data;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {
	private String errorCode;
	private String errorDesc;
}
