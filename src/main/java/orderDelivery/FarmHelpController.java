package orderDelivery;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
	
@RestController
public class FarmHelpController {

	@Autowired
	private FarmHelpService farmHelpService;
	
	private static final Logger logger = LoggerFactory.getLogger(FarmerHelpApplication.class);
	
	@RequestMapping("/orders")
	public List<OrderAccount> getAllOrders() {
		
		List<OrderAccount> orders = farmHelpService.getAllOrders();
		if(orders.isEmpty()) {
			logger.info("No orders placed yet.");
		}
		return orders;
	}
	
	@RequestMapping("/orders/{foo}")
	public OrderAccount getOrder(@PathVariable("foo") String orderNumber) {
		return farmHelpService.getOrder(orderNumber);	
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/orders")
	public void addOrder(@Valid @RequestBody OrderRequest orderRequest) {
		farmHelpService.addOrder(orderRequest.getOrderNumber(),orderRequest.getFarmId(),orderRequest.getDeliveryDateTime(),orderRequest.getDurationValue());
	
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/orders/{foo}")
	public void updateOrderStatus(@PathVariable("foo") String orderNumber,String status) {
		farmHelpService.updateOrderStatus(orderNumber, status);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/orders/{id}")
	public void cancelOrder(@PathVariable("id") String orderNumber ) {
		farmHelpService.cancelOrder(orderNumber);
	}
	
	
}
