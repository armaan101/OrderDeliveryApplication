package orderDelivery;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_account")

public class OrderAccount {
	
	@Id
	public String orderNumber;
	public String farmId;
	public Date deliveryDateTime;
	public int durationValue; 
	public String statusValue;
	
	
	public OrderAccount() {
		
	}
	
	public OrderAccount(String orderNumber, String farmId, Date deliveryDateTime, int durationValue) {
		super();
		this.orderNumber = orderNumber;
		this.farmId = farmId;
		
		this.deliveryDateTime = deliveryDateTime ;
		this.durationValue = durationValue ;
		this.statusValue = "Requested";
	}
	
	public String getFarmId() {
		return farmId;
	}

	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}

	public Date getDeliveryDateTime() {
		return deliveryDateTime;
	}

	public void setDeliveryDateTime(Date deliveryDateTime) {
		this.deliveryDateTime = deliveryDateTime;
	}

	public int getDurationValue() {
		return durationValue;
	}

	public void setDurationValue(int duration) {
		this.durationValue = duration;
	}

	public String getStatusValue() {
		return statusValue;
	}

	public void setStatusValue(String status) {
		if(status.equals("InProgress")||status.equals("Requested")||status.equals("Delivered"))
			this.statusValue = status;
		
	}

	public String getOrderNumber() {
		return orderNumber;
	}
	
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
}
