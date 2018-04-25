package workshop.toy.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import workshop.toy.models.OrderH;

import java.sql.Timestamp;

public interface OrderHRepository  extends CrudRepository<OrderH, Integer> {

    @Modifying
    @Query("INSERT INTO ORDER_H(ID, ORDER_NO, ORDER_DT, FULLNAME, ADDRESS1, ADDRESS2, CITY, PROVINCE, POST_CODE, EMAIL) VALUES (:id, :orderNo, :orderDate, :fullname, :address1, :address2, :city, :province, :postCode, :email)")
    void insert(@Param("id") int id, @Param("orderNo") String orderNo, @Param("orderDate") Timestamp orderDate, @Param("fullname") String fullname, @Param("address1") String address1, @Param("address2") String address2, @Param("city") String city,@Param("province") String province, @Param("postCode") String postCode, @Param("email") String email);

}
