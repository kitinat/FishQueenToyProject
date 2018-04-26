package workshop.toy.models;

public class OrderD {
    private int id;
    private int order_h_id;
    private int product_id;
    private int qty;
    private double unit_price;

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_h_id() {
        return order_h_id;
    }

    public void setOrder_h_id(int order_h_id) {
        this.order_h_id = order_h_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
