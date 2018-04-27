package workshop.toy.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import workshop.toy.models.Product;
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
    public void successSearch() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Test1", 1, 1, 1, 20.00, 10, "Neutral", "Baby", "CoolKidz", "In Stock"));
        products.add(new Product(2, "Test2", 1, 1, 1, 20.00, 10, "Neutral", "Baby", "CoolKidz", "In Stock"));
        given(productRepository.search("all", "all"))
                .willReturn(products);

        ResponseEntity<List> response
                = restTemplate.getForEntity("/rest/product/all/all", List.class);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, response.getBody().size());
    }


    @Test
    public void successGetProductDetail() {
        Product product = new Product(1, "Test1", 2, 3, 4, 20.00, 10, "Neutral", "Baby", "CoolKidz", "In Stock");
        given(productRepository.getProductById(1))
                .willReturn(product);

        ResponseEntity<Product> response
                = restTemplate.getForEntity("/rest/product/1", Product.class);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().getId());
        assertEquals("Test1", response.getBody().getProduct_name());
        assertEquals(2, response.getBody().getGender_id());
        assertEquals(3, response.getBody().getAge_id());
        assertEquals(4, response.getBody().getBrand_id());
        assertEquals(20.00, response.getBody().getPrice(), 2);
        assertEquals(10, response.getBody().getQty());
        assertEquals("Neutral", response.getBody().getGender_name());
        assertEquals("Baby", response.getBody().getAge_name());
        assertEquals("CoolKidz", response.getBody().getBrand_name());
        assertEquals("In Stock", response.getBody().getAvailability());
    }
}
