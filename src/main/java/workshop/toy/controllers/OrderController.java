package workshop.toy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import workshop.toy.models.*;
import workshop.toy.repositories.OrderDRepository;
import workshop.toy.repositories.OrderHRepository;
import workshop.toy.repositories.ProductRepository;
import workshop.toy.util.EmailUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @GetMapping("/stock/cart/{cartId}")
    public Cart updateCartWithCurrentStock(@PathVariable String cartId) {
        Cart cart = manageCart.getCart(cartId);
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

    @GetMapping("/stock/product/{cartId}")
    public void updateStock(@PathVariable String cartId) {
        Cart cart = manageCart.getCart(cartId);
        for (Map.Entry<String, CartItem> entry: cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            int stockQty = productRepository.getStockQtyById(Integer.parseInt(cartItem.getProduct_id()));
            if(cartItem.getQty() <= stockQty){
                System.out.println("Product id="+cartItem.getProduct_id());
                System.out.println("CartItem.Qty="+cartItem.getQty());
                System.out.println("stockQty="+stockQty);
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
    public OrderD createOrderD(@RequestBody OrderD orderD) {
        return orderDRepository.save(orderD);
    }

    @PostMapping("/order/email/{cartId}")
    public void sendMail(@PathVariable String cartId, @RequestBody OrderH orderH) {
        String emailTo = orderH.getEmail();
        String subject = "Confirm Order ID : "+orderH.getId();
        StringBuffer content = new StringBuffer();

        content.append("Order ID : ").append(orderH.getId()).append("\n");
        content.append("Order Date : ").append(orderH.getOrderDate()).append("\n\n");
        Cart cart = manageCart.getCart(cartId);
        int no = 1;
        BigDecimal totalPrice = new BigDecimal("0");
        for (Map.Entry<String, CartItem> entry: cart.getItems().entrySet()){
            CartItem item = entry.getValue();
            content.append("Item ").append(no++).append(" : \n");
            content.append("Product Name : ").append(item.getProduct_name()).append("\n");
            content.append("Unit Price : ").append(item.getPrice()).append("\n");
            content.append("Qty : ").append(item.getQty()).append("\n\n");

            totalPrice = totalPrice.add(new BigDecimal(item.getPrice()).setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal(item.getQty())).setScale(2, RoundingMode.HALF_UP));
        }

        content.append("Total price : ").append(totalPrice.doubleValue());

        EmailUtil.sendEmail(emailTo, subject, content.toString());
    }

}
