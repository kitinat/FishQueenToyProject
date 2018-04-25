package workshop.toy.repositories;

import org.springframework.data.repository.CrudRepository;
import workshop.toy.models.Age;
import workshop.toy.models.Product;

public class ProductRepository extends CrudRepository<Product, Integer> {

}
