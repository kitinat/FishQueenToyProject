package workshop.toy.controllers;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import workshop.toy.models.Cart;
import workshop.toy.models.CartItem;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void successAddCart() throws Exception {
        CartItem cartItem = new CartItem();
        cartItem.setProduct_id("1");
        HttpEntity<CartItem> cartItemHttpEntity = new HttpEntity<>(cartItem);
        ResponseEntity<Cart> response
                = restTemplate.postForEntity("/rest/cart/111", cartItemHttpEntity, Cart.class);

        assertEquals(200, response.getStatusCode().value());
    }
}
