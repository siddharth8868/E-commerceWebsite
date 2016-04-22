package controllers;

import helper.ConnectionPool;
import helper.MyConnection;
import helper.PasswordUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Utility.OrderDB;
import Utility.UserDB;
import beans.Order;
import beans.User;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ConnectionPool pool;
    Connection conn;
	java.sql.PreparedStatement ps,ps1;
    public UserController() {
        super();
    
    }

    
	public void init(ServletConfig config) throws ServletException {
		conn=null;
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hts = request.getSession(true);

		if(request.getParameter("access").equals("login")){
			int result = login(request.getParameter("username"),request.getParameter("password"),request);
			if(result==1){
				String loginType = (String)hts.getAttribute("loginType");
				if(loginType.equals("normal")){
					request.getRequestDispatcher("/index.jsp").forward(request, response);
					return;
				}
				else if(loginType.equals("viewOrders")){
					OrderDB orderDB=new OrderDB();
					ArrayList<Order> orders =orderDB.getOrders((int)hts.getAttribute("userID"));
					request.setAttribute("orders",orders);
					request.getRequestDispatcher("/ordersList.jsp").forward(request, response);
					return;
				}
				else if(loginType.equals("checkOut")){
					System.out.println("4");
					request.getRequestDispatcher("/cart.jsp").forward(request, response);
				}
	            
			}
			else{
				request.setAttribute("error","user id or password didnot matched");
	            request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
		else if(request.getParameter("access").equals("signup")){
			try{
				pool = ConnectionPool.getInstance();
	            conn = pool.getcConnection();
				//conn = MyConnection.getConnection();
	            User user = new User();
	            user.setFirstName(request.getParameter("firstName"));
	            user.setLastName(request.getParameter("lastName"));
	            user.setEmail(request.getParameter("emailAddr"));
	            user.setAddressOneField(request.getParameter("address1Field"));
	            user.setAddressTwoField(request.getParameter("address2Field"));
	            user.setCity(request.getParameter("city"));
	            user.setState(request.getParameter("state"));
	            user.setPostCode(request.getParameter("zipPostalCode"));
	            user.setCountry(request.getParameter("country"));
	            user.setPassword(request.getParameter("password"));
	            user.setRole("customer");
	            ps = conn.prepareStatement("INSERT INTO USER(firstName,lastName,emailAddr,address1Field,address2Field,city,state,zipPostalCode,country,pass,role) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			
				
			int i= UserDB.addUser(user);
			i=login(request.getParameter("emailAddr"),request.getParameter("password"), request);
			if(i==1){
				if(request.getParameter("loginType").equals("checkOut"))
					request.getRequestDispatcher("/OrderController?action=checkOut").forward(request, response);
				else {
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
			}
			
			
			}
			catch(Exception e){
				System.out.print("Email ID already exits, try to login with a different email id");
	            request.setAttribute("error","email id already exits please try to give different email id");
	            request.getRequestDispatcher("/userInfo.jsp").forward(request, response);
				e.printStackTrace();
			}
		}
		
	}
	

	protected int login(String username, String password,HttpServletRequest request) {
		try{
			pool = ConnectionPool.getInstance();
            conn = pool.getcConnection();
			//conn = MyConnection.getConnection();
            ps = conn.prepareStatement("select salt from USER where emailAddr=?");
            ps.setString(1,username);
			ResultSet rs= ps.executeQuery();
			
			String salt = null;
			while (rs.next()) {
				salt = rs.getString("salt");
			}
			
			String hashAndSalt = PasswordUtil.hashPassword(password + salt);
            
			ps1 = conn.prepareStatement("select firstName,userID from USER where emailAddr=? and pass=?");
			ps1.setString(1,username);
			ps1.setString(2,hashAndSalt);
			rs= ps1.executeQuery();
			
			while(rs.next()){
				request.getSession(true).setAttribute("firstName",(String) rs.getString("firstName"));
				request.getSession(true).setAttribute("userID",rs.getInt("userID"));
				return 1;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}

}
