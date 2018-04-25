package workshop.toy.models;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private String id;
    private Map<String, CartItem> items = new HashMap<String,CartItem>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<String, CartItem> items) {
        this.items = items;
    }
}
