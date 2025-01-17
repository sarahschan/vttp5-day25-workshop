package sg.edu.nus.iss.paf_day25_wsA.util;

import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.paf_day25_wsA.model.Order;
import sg.edu.nus.iss.paf_day25_wsA.model.OrderDetail;

@Component
public class OrderUtil {
    
    public Order jsonToPojo(String json) {
        
        JsonObject jsonObject = Json.createReader(new StringReader(json)).readObject();

        String customerName = jsonObject.getString("customer_name");
        LocalDate orderDate = LocalDate.parse(jsonObject.getString("order_date"));
        String shipAddress = jsonObject.getString("ship_address");
        String notes = jsonObject.getString("notes");
        float tax = Float.parseFloat(jsonObject.getString("tax"));

        List<OrderDetail> orderDetails = new ArrayList<>();
        
        JsonArray lineItems = jsonObject.getJsonArray("line_items");
        for (int i = 0; i < lineItems.size(); i++) {
            JsonObject orderDetailJson = lineItems.getJsonObject(i);
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(orderDetailJson.getString("product"));
            orderDetail.setUnitPrice(Float.parseFloat(orderDetailJson.getString("unit_price")));
            orderDetail.setDiscount(Float.parseFloat(orderDetailJson.getString("discount")));
            orderDetail.setQuantity(orderDetailJson.getInt("quantity"));
            orderDetails.add(orderDetail);
        }

        Order order = new Order();
            order.setOrderDate(orderDate);
            order.setCustomerName(customerName);
            order.setShipAddress(shipAddress);
            order.setNotes(notes);
            order.setTax(tax);
            order.setOrderDetails(orderDetails);

        return order;

    }
}
