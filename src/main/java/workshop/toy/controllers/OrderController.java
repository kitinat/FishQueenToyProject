package workshop.toy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import workshop.toy.models.*;
import workshop.toy.repositories.OrderDRepository;
import workshop.toy.repositories.OrderHRepository;
import workshop.toy.repositories.ProductRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class OrderController {

    @Autowired
    private ManageCart manageCart;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderHRepository orderHRepository;
    @Autowired
    OrderDRepository orderDRepository;


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

    @PostMapping("/stock/cart/{cartId}")
    public Cart updateCartWithCurrentStock(@PathVariable String cartId) {
        Cart cart = manageCart.getCart(cartId);
        String isAvailable = "Y";
        for (Map.Entry<String, CartItem> entry: cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            int stockQty = productRepository.getStockQtyById(Integer.parseInt(cartItem.getProduct_id()));
            if(cartItem.getQty() > stockQty){
                cartItem.setQty(stockQty);
                if(stockQty == 0){
                    cartItem.setAvailability("Out of Stock");
                }
            }
        }
        return cart;
    }

    @PostMapping("/stock/product/{cartId}")
    public void updateStock(@PathVariable String cartId) {
        Cart cart = manageCart.getCart(cartId);
        for (Map.Entry<String, CartItem> entry: cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            int stockQty = productRepository.getStockQtyById(Integer.parseInt(cartItem.getProduct_id()));
            if(cartItem.getQty() <= stockQty){
               productRepository.updateStockQty(Integer.parseInt(cartItem.getProduct_id()), (stockQty-cartItem.getQty()));
            }
        }
    }

    @PostMapping("/orderH")
    public OrderH createOrderH(@RequestBody OrderH orderH) {
        orderH.setOrderDate(new Timestamp(System.currentTimeMillis()));
        return orderHRepository.save(orderH);
    }

    @PostMapping("/orderD")
    public OrderD createOrderH(@PathVariable OrderD orderD) {
        return orderDRepository.save(orderD);
    }



}
