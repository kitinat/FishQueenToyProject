package workshop.toy.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import workshop.toy.models.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query("SELECT PRODUCT.ID, PRODUCT_NAME, GENDER_ID, AGE_ID, BRAND_ID, PRICE, QTY, GENDER_NAME, AGE_NAME, BRAND_NAME,\n" +
            " CASE WHEN QTY > 0 THEN 'In Stock' ELSE 'Out of Stock' END AVAILABILITY\n" +
            " FROM PRODUCT LEFT JOIN GENDER ON PRODUCT.GENDER_ID=GENDER.ID\n" +
            " LEFT JOIN AGE ON PRODUCT.AGE_ID = AGE.ID\n" +
            " LEFT JOIN BRAND ON PRODUCT.BRAND_ID = BRAND.ID\n" +
            " WHERE CASE WHEN :ageId = 'all' THEN TRUE ELSE AGE_ID=:ageId END\n" +
            " AND CASE WHEN :genderId = 'all' THEN TRUE\n" +
            " WHEN :genderId = '2' THEN GENDER_ID IN (1,2)\n" +
            " WHEN :genderId = '3' THEN GENDER_ID IN (1,3)\n" +
            " ELSE GENDER_ID=:genderId END\n" +
            " ORDER BY PRODUCT_NAME;")
    List<Product> search(@Param("ageId") String ageId,@Param("genderId")  String genderId);

    @Query("SELECT PRODUCT.ID, PRODUCT_NAME, GENDER_ID, AGE_ID, BRAND_ID, PRICE, QTY, GENDER_NAME, AGE_NAME, BRAND_NAME,\n" +
            " CASE WHEN QTY > 0 THEN 'In Stock' ELSE 'Out of Stock' END AVAILABILITY\n" +
            " FROM PRODUCT LEFT JOIN GENDER ON PRODUCT.GENDER_ID=GENDER.ID\n" +
            " LEFT JOIN AGE ON PRODUCT.AGE_ID = AGE.ID\n" +
            " LEFT JOIN BRAND ON PRODUCT.BRAND_ID = BRAND.ID\n" +
            " WHERE PRODUCT.ID=:id")
    Product getProductById(@Param("id") int id);

    @Query("SELECT QTY FROM PRODUCT WHERE ID=:id")
    int getStockQtyById(@Param("id") int id);

    @Modifying
    @Query("UPDATE PRODUCT SET QTY=:qty WHERE ID=:id")
    void updateStockQty(@Param("id") int id, @Param("qty") int qty);

}
