package sg.edu.nus.iss.paf_day25_wsA.util;

import org.springframework.stereotype.Component;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.paf_day25_wsA.model.Order;
import sg.edu.nus.iss.paf_day25_wsA.model.OrderDetail;

@Component
public class JsonSerializer {
    
    public String pojoToJson(Order order){
        
        JsonArrayBuilder lineItemsArrayBuilder = Json.createArrayBuilder();

        for (OrderDetail od : order.getOrderDetails()) {
            JsonObject orderDetailJObject = Json.createObjectBuilder()
                                            .add("product", od.getProduct())
                                            .add("unit_price", od.getUnitPrice())
                                            .add("discount", od.getDiscount())
                                            .add("quantity", od.getQuantity())
                                            .build();
            lineItemsArrayBuilder.add(orderDetailJObject);
        }

        JsonObject jsonObject = Json.createObjectBuilder()
                                    .add("customer_name", order.getCustomerName())
                                    .add("order_date", order.getOrderDate().toString())
                                    .add("ship_address", order.getShipAddress())
                                    .add("notes", order.getNotes())
                                    .add("tax", order.getTax())
                                    .add("line_items", lineItemsArrayBuilder.build())
                                    .build();

        return jsonObject.toString();
        
    }
}
