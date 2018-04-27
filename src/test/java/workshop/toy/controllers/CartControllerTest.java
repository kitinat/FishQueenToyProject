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
import workshop.toy.models.ManageCart;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CartControllerTest {

    @Autowired
    private ManageCart manageCart;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void successAddCart() {
        CartItem cartItem = new CartItem("2", "43 Piece dinner Set", "CoolKidz", "Neutral", "3_to_5", 12.95, "In Stock", 2, 10);
        HttpEntity<CartItem> cartItemHttpEntity = new HttpEntity<>(cartItem);
        ResponseEntity<Cart> response
                = restTemplate.postForEntity("/rest/cart/111", cartItemHttpEntity, Cart.class);

        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void successGetCartDetail() {
        Cart cart = new Cart();
        cart.setId("111");
        CartItem cartItem1 = new CartItem("1", "Balance Training Bicycle", "SportsFun", "Neutral", "3_to_5", 119.95, "In Stock", 1, 10);
        CartItem cartItem2 = new CartItem("2", "43 Piece dinner Set", "CoolKidz", "Neutral", "3_to_5", 12.95, "In Stock", 2, 10);
        cart.getItems().put(cartItem1.getProduct_id(), cartItem1);
        cart.getItems().put(cartItem2.getProduct_id(), cartItem2);
        manageCart.putCart(cart.getId(), cart);

        ResponseEntity<Collection> response
                = restTemplate.getForEntity("/rest/cart/111", Collection.class);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void updateCartByZeroQty() {
        Cart cart = new Cart();
        cart.setId("111");
        CartItem cartItem1 = new CartItem("1", "Balance Training Bicycle", "SportsFun", "Neutral", "3_to_5", 119.95, "In Stock", 1, 10);
        CartItem cartItem2 = new CartItem("2", "43 Piece dinner Set", "CoolKidz", "Neutral", "3_to_5", 12.95, "In Stock", 2, 10);
        cart.getItems().put(cartItem1.getProduct_id(), cartItem1);
        cart.getItems().put(cartItem2.getProduct_id(), cartItem2);
        manageCart.putCart(cart.getId(), cart);

        ResponseEntity<Cart> response
                = restTemplate.getForEntity("/rest/cart/111/1/0", Cart.class);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("111", response.getBody().getId());
        assertEquals(1, response.getBody().getItems().size());
    }

    @Test
    public void updateCartByNonZeroQty() {
        Cart cart = new Cart();
        cart.setId("111");
        CartItem cartItem1 = new CartItem("1", "Balance Training Bicycle", "SportsFun", "Neutral", "3_to_5", 119.95, "In Stock", 1, 10);
        CartItem cartItem2 = new CartItem("2", "43 Piece dinner Set", "CoolKidz", "Neutral", "3_to_5", 12.95, "In Stock", 2, 10);
        cart.getItems().put(cartItem1.getProduct_id(), cartItem1);
        cart.getItems().put(cartItem2.getProduct_id(), cartItem2);
        manageCart.putCart(cart.getId(), cart);

        ResponseEntity<Cart> response
                = restTemplate.getForEntity("/rest/cart/111/1/3", Cart.class);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("111", response.getBody().getId());
        assertEquals(2, response.getBody().getItems().size());
        assertEquals(3, response.getBody().getItems().get("1").getQty());
    }

    @Test
    public void deleteCart() {
        Cart cart1 = new Cart();
        cart1.setId("111");
        CartItem cartItem1 = new CartItem("1", "Balance Training Bicycle", "SportsFun", "Neutral", "3_to_5", 119.95, "In Stock", 1, 10);
        CartItem cartItem2 = new CartItem("2", "43 Piece dinner Set", "CoolKidz", "Neutral", "3_to_5", 12.95, "In Stock", 2, 10);
        cart1.getItems().put(cartItem1.getProduct_id(), cartItem1);
        cart1.getItems().put(cartItem2.getProduct_id(), cartItem2);
        manageCart.putCart(cart1.getId(), cart1);
        Cart cart2 = new Cart();
        cart2.setId("222");
        CartItem cartItem3 = new CartItem("2", "43 Piece dinner Set", "CoolKidz", "Neutral", "3_to_5", 12.95, "In Stock", 1, 5);
        cart2.getItems().put(cartItem3.getProduct_id(), cartItem3);
        manageCart.putCart(cart2.getId(), cart2);

        ResponseEntity<Object> response
            = restTemplate.getForEntity("/rest/cart/deletion/111", Object.class);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, manageCart.getCartMap().size());

    }
}