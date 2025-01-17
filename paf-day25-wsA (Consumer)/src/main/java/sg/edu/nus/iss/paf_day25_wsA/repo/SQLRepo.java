package sg.edu.nus.iss.paf_day25_wsA.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_day25_wsA.model.Order;
import sg.edu.nus.iss.paf_day25_wsA.model.OrderDetail;
import sg.edu.nus.iss.paf_day25_wsA.model.exceptions.UnableToInsertOrderDetailException;
import sg.edu.nus.iss.paf_day25_wsA.model.exceptions.UnableToInsertOrderException;

@Repository
public class SQLRepo {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertOrder(Order order) {
        
        KeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                
                PreparedStatement ps = con.prepareStatement(Query.SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
                    ps.setDate(1, java.sql.Date.valueOf(order.getOrderDate()));
                    ps.setString(2, order.getCustomerName());
                    ps.setString(3, order.getShipAddress());
                    ps.setString(4, order.getNotes());
                    ps.setFloat(5, order.getTax());

                return ps;
            }
        };

        jdbcTemplate.update(psc, keyHolder);

        int generatedOrderId = keyHolder.getKey().intValue();

        if (generatedOrderId <= 0) {
            throw new UnableToInsertOrderException("Unable to insert order into DB");
        }

        return generatedOrderId;

    }


    public boolean insertOrderDetail(OrderDetail orderDetail) {
    
        try {
            
            jdbcTemplate.update(Query.SQL_INSERT_ORDERDETAIL,
                                orderDetail.getProduct(),
                                orderDetail.getUnitPrice(),
                                orderDetail.getDiscount(),
                                orderDetail.getQuantity(),
                                orderDetail.getOrderId());

            return true;

        } catch (DataAccessException error) {
            throw new UnableToInsertOrderDetailException("Unable to insert order detail in DB");
        }
        
    }
}
