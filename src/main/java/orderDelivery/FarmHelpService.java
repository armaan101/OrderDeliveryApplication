package orderDelivery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class FarmHelpService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(FarmerHelpApplication.class);
	
	public List<OrderAccount> getAllOrders() {
		
		List<OrderAccount> orders = new ArrayList<>();
		orderRepository.findAll().forEach(orders::add);
		
		return orders;
	}
	
	public OrderAccount getOrder(String orderNumber) {
		Optional<OrderAccount> order = orderRepository.findById(orderNumber);
		OrderAccount existingOrder = order.get();
		if(existingOrder == null) {
			logger.info("No orders placed yet.");
		}
		return existingOrder;
	}
	
	public void addOrder(String orderNumber, String farmId, String deliveryDateTime, int durationValue) {
		Date updatedDeliveryDateTime= stringDateToDate(deliveryDateTime);
		System.out.println(updatedDeliveryDateTime);
		if(updatedDeliveryDateTime==null) {
			return;
		}
		boolean isSlotBooked = this.isBooked(farmId, updatedDeliveryDateTime, durationValue);
		System.out.println(isSlotBooked+ "slot");
		if(!isSlotBooked && updatedDeliveryDateTime.getTime() >=System.currentTimeMillis()) {
			orderRepository.save(new OrderAccount(orderNumber, farmId, updatedDeliveryDateTime, durationValue));
			logger.info("New water order for farm "+farmId + " created");
		}
		else {
			logger.error("Error while creating order request");
		}
			
	}
	public Date stringDateToDate(String deliveryDateTime){
	    Date result = null;
	    try{
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        result  = dateFormat.parse(deliveryDateTime);
	    }

	    catch(ParseException e){
	    	logger.error("Error while creating order request. Incorrect date format");
	        //e.printStackTrace();

	    }
	    return result ;
	}
	
	public boolean isBooked(String farmId, Date newOrderDateTime, int newOrderDuration) {
		List<OrderAccount> orderListByFarmId = orderRepository.findByFarmId(farmId);
		if(orderListByFarmId!=null) {
			for(OrderAccount order : orderListByFarmId) {
				long orderDeliveryStartDateTime = order.getDeliveryDateTime().getTime();
				long orderDeliveryEndDateTime = addHours(order.getDeliveryDateTime(),order.getDurationValue());
				if(orderDeliveryStartDateTime<= newOrderDateTime.getTime()&&newOrderDateTime.getTime()<orderDeliveryEndDateTime) {
					return true;
				}
			}
		}
		else {
			return true;
		}
		return false;
	}
	
	public long addHours(Date orderDateTime , int duration) {
		final long HOUR = 3600*1000;
//		final long MINUTES = 60*1000;
				
        long updatedDateTime = orderDateTime.getTime() + duration*HOUR;
//		long updatedDateTime = orderDateTime.getTime() + duration*MINUTES;
		return updatedDateTime;
		
	}
  
	public void updateOrderStatus(String orderNumber, String status) {
		OrderAccount existingOrder = getOrder(orderNumber);
		if(existingOrder!=null && !(status.equals("Delivered"))) {
			existingOrder.setStatusValue(status);
			orderRepository.save(existingOrder);
			logger.info("Water delivery to farm " + existingOrder.getFarmId() + " is " + status);
		}
		else if (existingOrder==null) {
			logger.info("No such order present. ");
		}
		
	}
	
	public void cancelOrder(String orderNumber) {
		OrderAccount existingOrder = getOrder(orderNumber);
		if(existingOrder!=null && !(existingOrder.getStatusValue().equals("Delivered"))) {
			orderRepository.deleteById(existingOrder.getOrderNumber());
		}
		else if (existingOrder.getStatusValue().equals("Delivered")) {
			logger.info("The order is already delivered. ");
		}
		
	}
	
	@Scheduled(fixedRate = 1000)
	public void logStatus() {
		long currentDateTime = System.currentTimeMillis();
		List<OrderAccount> orderList = getAllOrders();
		if(orderList!=null) {
			for(OrderAccount order : orderList) {
				long startDeliveryDateTime = order.getDeliveryDateTime().getTime();
                long endDeliveryDateTime = addHours(order.getDeliveryDateTime(),order.getDurationValue());
				if(startDeliveryDateTime<=currentDateTime && endDeliveryDateTime>currentDateTime && order.getStatusValue().equals("Requested")) {
					updateOrderStatus(order.getOrderNumber(),"InProgress");
				}
				else if (endDeliveryDateTime<=currentDateTime && order.getStatusValue().equals("InProgress")) {
					updateOrderStatus(order.getOrderNumber(),"Delivered");
				
				}
			}
		}
	     
	}
			
		
}
	
	
	

