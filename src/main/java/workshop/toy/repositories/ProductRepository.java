package workshop.toy.repositories;

import org.springframework.data.repository.CrudRepository;
import workshop.toy.models.Age;

public class ProductRepository extends CrudRepository<Product, Integer> {

}
