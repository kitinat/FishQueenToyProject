package workshop.toy.controllers;

import org.junit.Test;
import workshop.toy.models.Cart;
import workshop.toy.models.CartItem;
import workshop.toy.models.ManageCart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CartControllerAddCartItemTest {

    @Test
    public void add_new_item_to_empty_cart() {
        CartController cartController = new CartController();
        cartController.setManageCart(new ManageCart());
        CartItem cartItem = new CartItem();
        cartItem.setProduct_id("1");
        Cart cart = cartController.addCartItem("1", cartItem);
        assertNotNull(cart);
        assertEquals(1, cart.getItems().size());
    }

}
