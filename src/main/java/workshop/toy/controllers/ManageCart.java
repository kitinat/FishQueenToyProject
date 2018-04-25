package workshop.toy.controllers;

import org.springframework.stereotype.Component;
import workshop.toy.models.Cart;

import java.util.HashMap;
import java.util.Map;

@Component
public class ManageCart {
    private Map<String, Cart> cartMap = new HashMap<String,Cart>();

    public Map<String, Cart> getCartMap() {
        return cartMap;
    }

    public void setCartMap(Map<String, Cart> cartMap) {
        this.cartMap = cartMap;
    }
}
