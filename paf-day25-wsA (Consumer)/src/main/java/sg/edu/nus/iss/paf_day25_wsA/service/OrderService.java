package sg.edu.nus.iss.paf_day25_wsA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.paf_day25_wsA.model.Order;
import sg.edu.nus.iss.paf_day25_wsA.model.OrderDetail;
import sg.edu.nus.iss.paf_day25_wsA.repo.SQLRepo;


@Service
public class OrderService {

    @Autowired
    SQLRepo sqlRepo;


    public int insertOrder(Order order) {
        return sqlRepo.insertOrder(order);
    }


    public boolean insertOrderDetail(OrderDetail orderDetail) {
        return sqlRepo.insertOrderDetail(orderDetail);
    }


    @Transactional
    public void saveOrderToDatabase(Order order) {
        
        // insert the order
        int generatedOrderId = insertOrder(order);
        System.out.printf("Order %d inserted to database\n", generatedOrderId);

        // insert the orderDetails
        List<OrderDetail> orderDetails = order.getOrderDetails();
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrderId(generatedOrderId);
            insertOrderDetail(orderDetail);
        }
        System.out.printf("All OrderDetails for order %d inserted to database\n", generatedOrderId);

        System.out.printf("Order %d saved to database\n", generatedOrderId);

    }
}
