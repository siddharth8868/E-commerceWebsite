package Utility;

import helper.ConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.mysql.jdbc.PreparedStatement;

import beans.Cart;
import beans.Order;
import beans.OrderItem;

public class OrderDB {
	ConnectionPool pool;
	Connection conn=null;
	java.sql.PreparedStatement ps;
	
	public int addOrder(Order order){
		pool = ConnectionPool.getInstance();
        conn = pool.getcConnection();
        try {
			ps =conn.prepareStatement("insert into ORDERS(dates,userID,taxRate,totalCost,paid) values(?,?,?,?,?)");
			ps.setDate(1,new java.sql.Date((new java.util.Date()).getTime()));
			ps.setInt(2, order.getUserID());
			ps.setFloat(3,order.getTaxRate());
			ps.setFloat(4, order.getTotalCost());
			ps.setBoolean(5,true);
			int res= ps.executeUpdate();
			if(res==1){
				ps=conn.prepareStatement("select orderNumber from ORDERS");
				ResultSet rs=ps.executeQuery();
				rs.last();
				int orderNumber = rs.getInt("orderNumber");
				for(OrderItem orderItem : order.getOrderItem()){
					ps =conn.prepareStatement("insert into ORDERITEM(orderNumber,productCode,quantity) values(?,?,?)");
					ps.setInt(1,orderNumber);
					ps.setString(2,orderItem.getProduct().getProductCode());
					ps.setInt(3,orderItem.getQuantity());
					ps.executeUpdate();
				}
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
    }
	
	public ArrayList<Order> getOrders(int userID) {
		ArrayList<Order> orders=new ArrayList<Order>();
		Order order=null;
		 pool = ConnectionPool.getInstance();
	        conn = pool.getcConnection();
	        try {
				ps =conn.prepareStatement("select * from ORDERS where userID=?");
				ps.setInt(1,userID);
				ResultSet rs =ps.executeQuery();
				while(rs.next()){
					order = new Order();
					order.setOrderNumber(rs.getInt("orderNumber"));
					order.setDate(rs.getDate("dates"));
					order.setUserID(rs.getInt("userID"));
					order.setTaxRate(rs.getFloat("taxRate"));
					order.setTotalCost(rs.getFloat("totalCost"));
					order.setPaid(rs.getBoolean("paid"));
					
					orders.add(order);
				}
	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
		
		return orders;
		
	}

	public Order getOrderItem(int orderNumber) {
		Cart cart= new Cart();
		
		pool = ConnectionPool.getInstance();
        conn = pool.getcConnection();
        Order order =new Order();
        try {
			ps =conn.prepareStatement("select productCode,quantity from ORDERITEM where orderNumber=?");
			ps.setInt(1,orderNumber);
			ResultSet rs =ps.executeQuery();
			ProductDB productDB;
			while(rs.next()){
				productDB=new ProductDB();
				cart.addItemDirect(productDB.getProduct(rs.getString("productCode")), rs.getInt("quantity"));
			}
			System.out.println("hello");
			for(OrderItem orderitem : cart.getItems()){
				System.out.println(orderitem.getProduct().getProductName()+" = "+orderitem.getQuantity());
			}
			order.setOrderItem(cart.getItems());
			float tax;
			ps =conn.prepareStatement("select * from ORDERS where orderNumber=?");
			ps.setInt(1,orderNumber);
			rs =ps.executeQuery();
			while(rs.next()){
				order.setDate(rs.getDate("dates"));
				order.setOrderNumber(orderNumber);
				order.setPaid(rs.getBoolean("paid"));
				order.setTaxRate(rs.getFloat("taxRate"));
				order.setTotalCost(rs.getFloat("totalCost"));
			}

			
        }
        catch(Exception e){
        	e.printStackTrace();
        }
	
	return order;
	}

}
