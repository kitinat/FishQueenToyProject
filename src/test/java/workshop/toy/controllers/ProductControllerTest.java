package workshop.toy.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import workshop.toy.models.Age;
import workshop.toy.models.Product;
import workshop.toy.repositories.AgeRepository;
import workshop.toy.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void success() throws Exception {
        Product a1 = new Product(1, "Test1", 1, 1, 1, 20.00, 10, "Neutral", "Baby", "CoolKidz", "In Stock");
        Product a2 = new Product(2, "Test2", 1, 1, 1, 20.00, 10, "Neutral", "Baby", "CoolKidz", "In Stock");
        List<Product> products = new ArrayList<>();
        products.add(a1);
        products.add(a2);
        given(productRepository.search("all","all"))
                .willReturn(products);

        ResponseEntity<List> response
                = restTemplate.getForEntity("/rest/product/all/all", List.class);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, response.getBody().size());
    }
}
