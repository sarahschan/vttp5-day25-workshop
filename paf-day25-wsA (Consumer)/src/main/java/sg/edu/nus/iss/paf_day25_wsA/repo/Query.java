package sg.edu.nus.iss.paf_day25_wsA.repo;

public class Query {
    
    public static final String SQL_INSERT_ORDER = 
    """
        insert into orders
            (order_date, customer_name, ship_address, notes, tax)
        values
            (?, ?, ?, ?, ?)        
    """;


    public static final String SQL_INSERT_ORDERDETAIL = 
    """
        insert into order_details
            (product, unit_price, discount, quantity, order_id)
        values
            (?, ?, ?, ?, ?)
    """;
    
}
