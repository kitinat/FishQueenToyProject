package workshop.toy.models;

import org.springframework.data.annotation.Id;

public class OrderD {
    @Id
    private int id;
    private int orderHId;
    private int productId;
    private int qty;
    private double unitPrice;

    public OrderD(){}
    public OrderD(int orderHId, int productId, int qty, double unitPrice) {
        this.orderHId = orderHId;
        this.productId = productId;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderHId() {
        return orderHId;
    }

    public void setOrderHId(int orderHId) {
        this.orderHId = orderHId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
