package sg.edu.nus.iss.paf_day25_wsA.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import sg.edu.nus.iss.paf_day25_wsA.model.Order;
import sg.edu.nus.iss.paf_day25_wsA.model.OrderDetail;

@Service
public class OrderUtil {
    
    public Order createOrder(MultiValueMap<String, String> data) {
        
        String orderDate = data.getFirst("orderDate");
        String customerName = data.getFirst("customerName");
        String shipAddress = data.getFirst("shipAddress");
        String notes = data.getFirst("notes");
        String tax = data.getFirst("tax");

        List<OrderDetail> orderDetails = extractOrderDetails(data);
        
        Order order = new Order();
            order.setOrderDate(LocalDate.parse(orderDate));
            order.setCustomerName(customerName);
            order.setShipAddress(shipAddress);
            order.setNotes(notes);
            order.setTax(tax);
            order.setOrderDetails(orderDetails);

        return order;

    }

    public List<OrderDetail> extractOrderDetails(MultiValueMap<String, String> order) {
        
        List<String> products = order.get("orderDetails[][product]");
        List<String> unitPrices = order.get("orderDetails[][unitPrice]");
        List<String> discounts = order.get("orderDetails[][discount]");
        List<String> quantities = order.get("orderDetails[][quantity]");

        List<OrderDetail> orderDetails = new ArrayList<>();
        
        if (products != null) {
            for (int i = 0; i < products.size(); i++){
                OrderDetail detail = new OrderDetail();
                    detail.setProduct(products.get(i));
                    detail.setUnitPrice(unitPrices.get(i));
                    detail.setDiscount(discounts.get(i));
                    detail.setQuantity(Integer.parseInt(quantities.get(i)));
                orderDetails.add(detail);
            }
        }

        return orderDetails;
    }
}
