package orderDelivery;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderAccount, String>{

	List<OrderAccount> findByFarmId(String farmId);
	
	
}
