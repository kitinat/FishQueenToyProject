package workshop.toy.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import workshop.toy.models.OrderH;

import java.sql.Timestamp;

public interface OrderHRepository  extends CrudRepository<OrderH, Integer> {

}
