package workshop.toy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import workshop.toy.models.Cart;
import workshop.toy.models.CartItem;

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
        System.out.println("addCart");
        Cart cart = addCartItem(id, cartItem);

        return cart;
    }

    public Cart addCartItem(String id, CartItem cartItem) {
        Cart cart = manageCart.getCartMap().get(id);

        if (cart == null) {
            cart = new Cart();
            cart.setId(UUID.randomUUID().toString());
        }
        CartItem item = cart.getItems().get(cartItem.getProduct_id());
        if(item != null){
            item.setQty(cartItem.getQty() + item.getQty());
        } else {
            cart.getItems().put(cartItem.getProduct_id(), cartItem);
        }

        manageCart.getCartMap().put(cart.getId(), cart);
        return cart;
    }

    /*@GetMapping("/cart/{id}")
    public Cart getCartDetail(@PathVariable String id) {
        return manageCart.getCartMap().get(id);
    }

    @PostMapping("/cart/{id}/{productId}/{qty}")
    public Cart updateCart(@PathVariable String id, String productId, int qty) {
        Cart cart = manageCart.getCartMap().get(id);

        if(qty == 0){
            cart.getItems().remove(productId);
        }else{
            CartItem item = cart.getItems().get(productId);
            item.setQty(qty);
        }

        return cart;
    }*/
}
