package orderDelivery;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class OrderRequest {
	
	@NotBlank(message = "OrderNumber is mandatory")
	public String orderNumber;
	
	@NotBlank(message = "farmId is mandatory")
	public String farmId;
	
	@NotBlank(message = "deliveryDateTime is mandatory")
	public String deliveryDateTime;
	
	@NotNull(message = "duration may not be null")
	public int durationValue; 
	public String statusValue;
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getFarmId() {
		return farmId;
	}
	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}
	public String getDeliveryDateTime() {
		return deliveryDateTime;
	}
	public void setDeliveryDateTime(String deliveryDateTime) {
		this.deliveryDateTime = deliveryDateTime;
	}
	public int getDurationValue() {
		return durationValue;
	}
	public void setDurationValue(int durationValue) {
		this.durationValue = durationValue;
	}
	public String getStatusValue() {
		return statusValue;
	}
	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}
	
}
