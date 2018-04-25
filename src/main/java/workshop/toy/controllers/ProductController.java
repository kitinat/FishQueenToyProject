package workshop.toy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import workshop.toy.models.Product;
import workshop.toy.repositories.ProductRepository;

import java.util.List;

@RestController("/rest")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/product/{ageId}/{genderId}")
    public List<Product> search(@PathVariable String ageId, @PathVariable String genderId) {
        System.out.println(ageId);
        return null; //productRepository.search();
    }
}
