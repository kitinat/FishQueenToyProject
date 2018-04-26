package workshop.toy.models;

import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

public class OrderH {
    @Id
    private int id;
    private Timestamp orderDate;
    private String fullname;
    private String address1;
    private String address2;
    private String city;
    private String province;
    private String postcode;
    private String email;

    public OrderH(){}

    public OrderH(String fullname, String address1, String address2, String city, String province, String postcode, String email) {
        this.fullname = fullname;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.province = province;
        this.postcode = postcode;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
