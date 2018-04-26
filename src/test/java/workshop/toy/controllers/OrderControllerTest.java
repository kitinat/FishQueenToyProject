package workshop.toy.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import workshop.toy.models.*;
import workshop.toy.repositories.OrderDRepository;
import workshop.toy.repositories.OrderHRepository;
import workshop.toy.repositories.ProductRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private OrderHRepository orderHRepository;
    @MockBean
    private OrderDRepository orderDRepository;

    @Autowired
    private ManageCart manageCart;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void stockIsNotAvailableReturnN() throws Exception {
        Cart cart = new Cart();
        cart.setId("111");
        CartItem cartItem1 = new CartItem("1", "Balance Training Bicycle", "SportsFun", "3_to_5", 119.95, "In Stock", 2);
        CartItem cartItem2 = new CartItem("2", "43 Piece dinner Set", "CoolKidz", "3_to_5", 12.95, "In Stock", 13);
        cart.getItems().put(cartItem1.getProduct_id(), cartItem1);
        cart.getItems().put(cartItem2.getProduct_id(), cartItem2);
        manageCart.putCart(cart.getId(), cart);

        given(productRepository.getStockQtyById(1))
                .willReturn(10);
        given(productRepository.getStockQtyById(2))
                .willReturn(5);

        ResponseEntity<String> response
                = restTemplate.getForEntity("/rest/stock/111", String.class);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("N", response.getBody().toString());
    }

    @Test
    public void stockIsAvailableReturnY() throws Exception {
        Cart cart = new Cart();
        cart.setId("111");
        CartItem cartItem1 = new CartItem("1", "Balance Training Bicycle", "SportsFun", "3_to_5", 119.95, "In Stock", 2);
        CartItem cartItem2 = new CartItem("2", "43 Piece dinner Set", "CoolKidz", "3_to_5", 12.95, "In Stock", 13);
        cart.getItems().put(cartItem1.getProduct_id(), cartItem1);
        cart.getItems().put(cartItem2.getProduct_id(), cartItem2);
        manageCart.putCart(cart.getId(), cart);

        given(productRepository.getStockQtyById(1))
                .willReturn(20);
        given(productRepository.getStockQtyById(2))
                .willReturn(15);

        ResponseEntity<String> response
                = restTemplate.getForEntity("/rest/stock/111", String.class);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Y", response.getBody().toString());
    }

    @Test
    public void successUpdateCartWithCurrentStock() throws Exception {
        Cart cart = new Cart();
        cart.setId("111");
        CartItem cartItem1 = new CartItem("1", "Balance Training Bicycle", "SportsFun", "3_to_5", 119.95, "In Stock", 2);
        CartItem cartItem2 = new CartItem("2", "43 Piece dinner Set", "CoolKidz", "3_to_5", 12.95, "In Stock", 13);
        cart.getItems().put(cartItem1.getProduct_id(), cartItem1);
        cart.getItems().put(cartItem2.getProduct_id(), cartItem2);
        manageCart.putCart(cart.getId(), cart);

        given(productRepository.getStockQtyById(1))
                .willReturn(1);
        given(productRepository.getStockQtyById(2))
                .willReturn(0);

        ResponseEntity<Cart> response
                = restTemplate.getForEntity("/rest/stock/cart/111", Cart.class);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().getItems().get("1").getQty());
        assertEquals("In Stock", response.getBody().getItems().get("1").getAvailability());
        assertEquals(0, response.getBody().getItems().get("2").getQty());
        assertEquals("Out of Stock", response.getBody().getItems().get("2").getAvailability());
    }


    @Test
    public void successUpdateStock() throws Exception {
        Cart cart = new Cart();
        cart.setId("111");
        CartItem cartItem1 = new CartItem("1", "Balance Training Bicycle", "SportsFun", "3_to_5", 119.95, "In Stock", 2);
        CartItem cartItem2 = new CartItem("2", "43 Piece dinner Set", "CoolKidz", "3_to_5", 12.95, "In Stock", 3);
        cart.getItems().put(cartItem1.getProduct_id(), cartItem1);
        cart.getItems().put(cartItem2.getProduct_id(), cartItem2);
        manageCart.putCart(cart.getId(), cart);

        given(productRepository.getStockQtyById(1))
                .willReturn(10);
        given(productRepository.getStockQtyById(2))
                .willReturn(10);

        ResponseEntity<Object> response
                = restTemplate.getForEntity("/rest/stock/product/111", Object.class);

        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void successCreateOrderH() throws Exception {
        OrderH orderH = new OrderH("Mr.A1","9/99 Ladprao road","","Thailand","Bangkok","11111", "a1@gmail.com");
        given(orderHRepository.save(orderH)).willReturn(orderH);

        ResponseEntity<OrderH> response
                = restTemplate.postForEntity("/rest/orderH", orderH, OrderH.class);

        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void successCreateOrderD() throws Exception {
        OrderD orderD = new OrderD(1,1,2,119.95);
        given(orderDRepository.save(orderD)).willReturn(orderD);

        ResponseEntity<OrderD> response
                = restTemplate.postForEntity("/rest/orderD", orderD, OrderD.class);

        assertEquals(200, response.getStatusCode().value());
    }
}
