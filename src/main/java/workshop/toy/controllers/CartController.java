package workshop.toy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import workshop.toy.models.Cart;
import workshop.toy.models.CartItem;
import workshop.toy.models.ManageCart;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/rest")
public class CartController {

    public void setManageCart(ManageCart manageCart) {
        this.manageCart = manageCart;
    }

    @Autowired
    private ManageCart manageCart;

    @PostMapping("/cart/{id}")
    public Cart addCart(@PathVariable String id, @RequestBody CartItem cartItem) {
        return addCartItem(id, cartItem);
    }

    public Cart addCartItem(String id, CartItem cartItem) {
        Cart cart = manageCart.getCart(id);

        if (cart == null) {
            cart = new Cart();
            cart.setId(UUID.randomUUID().toString());
        }
        CartItem item = cart.getItems().get(cartItem.getProduct_id());
        if (item != null) {
            item.setQty(cartItem.getQty() + item.getQty());
        } else {
            cart.getItems().put(cartItem.getProduct_id(), cartItem);
        }

        manageCart.putCart(cart.getId(), cart);
        return cart;
    }

    @GetMapping("/cart/{id}")
    public Collection<CartItem> getCartDetail(@PathVariable String id) {
        return manageCart.getCart(id).getItems().values();
    }

    @GetMapping("/cart/{id}/{productId}/{qty}")
    public Cart updateCart(@PathVariable String id, @PathVariable String productId, @PathVariable int qty) {
        Cart cart = manageCart.getCart(id);

        if (qty == 0) {
            cart.getItems().remove(productId);
        } else {
            CartItem item = cart.getItems().get(productId);
            item.setQty(qty);
        }

        return cart;
    }

    @GetMapping("/cart/deletion/{id}")
    public void deleteCart(@PathVariable String id) {
        manageCart.removeCart(id);
    }
}