package workshop.toy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import workshop.toy.models.Cart;
import workshop.toy.models.CartItem;
import workshop.toy.models.ManageCart;
import workshop.toy.models.ShippingAddress;
import workshop.toy.repositories.OrderHRepository;
import workshop.toy.repositories.ProductRepository;

import java.util.Map;

@RestController
@RequestMapping("/rest/order")
public class OrderController {

    @Autowired
    private ManageCart manageCart;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderHRepository orderHRepository;


    @GetMapping("/stock/{cartId}")
    public String isAvailableStock(@PathVariable String cartId) {
        Cart cart = manageCart.getCart(cartId);
        String isAvailable = "Y";
        for (Map.Entry<String, CartItem> entry: cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            int stockQty = productRepository.getStockQtyById(Integer.parseInt(cartItem.getProduct_id()));
            if(cartItem.getQty() > stockQty){
                isAvailable = "N";
                break;
            }
        }
        return isAvailable;
    }



}
