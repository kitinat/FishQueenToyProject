package workshop.toy.models;

public class CartItem {
    private String product_id;
    private String product_name;
    private String brand_name;
    private String gender_name;
    private String age_name;
    private double price;
    private String availability;
    private int qty;
    private int stock_qty;

    public CartItem(){

    }
    public CartItem(String product_id, String product_name, String brand_name, String gender_name, String age_name, double price, String availability, int qty, int stock_qty) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.brand_name = brand_name;
        this.gender_name = gender_name;
        this.age_name = age_name;
        this.price = price;
        this.availability = availability;
        this.qty = qty;
        this.stock_qty = stock_qty;
    }


    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getGender_name() {
        return gender_name;
    }

    public void setGender_name(String gender_name) {
        this.gender_name = gender_name;
    }

    public String getAge_name() {
        return age_name;
    }

    public void setAge_name(String age_name) {
        this.age_name = age_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getStock_qty() {
        return stock_qty;
    }

    public void setStock_qty(int stock_qty) {
        this.stock_qty = stock_qty;
    }
}
