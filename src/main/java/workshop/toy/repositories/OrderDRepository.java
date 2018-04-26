package workshop.toy.repositories;

import org.springframework.data.repository.CrudRepository;
import workshop.toy.models.OrderD;

public interface OrderDRepository extends CrudRepository<OrderD, Integer> {
    OrderD save(OrderD obj);
}
