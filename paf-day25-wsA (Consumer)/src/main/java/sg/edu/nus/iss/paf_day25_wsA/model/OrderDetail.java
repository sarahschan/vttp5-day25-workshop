package sg.edu.nus.iss.paf_day25_wsA.model;

public class OrderDetail {
    
    private int id;
    private String product;
    private float unitPrice;
    private float discount;
    private int quantity;
    private int orderId;


    public OrderDetail() {
    }

    public OrderDetail(int id, String product, float unitPrice, float discount, int quantity, int order_id) {
        this.id = id;
        this.product = product;
        this.unitPrice = unitPrice;
        this.discount = discount;
        this.quantity = quantity;
        this.orderId = order_id;
    }


    @Override
    public String toString() {
        return "OrderDetail [id=" + id + ", product=" + product + ", unitPrice=" + unitPrice + ", discount=" + discount
                + ", quantity=" + quantity + ", order_id=" + orderId + "]";
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public float getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
    public float getDiscount() {
        return discount;
    }
    public void setDiscount(float discount) {
        this.discount = discount;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int order_id) {
        this.orderId = order_id;
    }
    
}
