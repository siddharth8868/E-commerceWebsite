package controllers;

import beans.Cart;
import Utility.OrderDB;
import Utility.ProductDB;
import Utility.UserDB;
import beans.Order;
import beans.OrderItem;
import beans.Product;
import beans.User;
import helper.ConnectionPool;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author siddharth
 */
public class OrderController extends HttpServlet {

    ProductDB productDB;
    ConnectionPool pool;
    Connection conn;
	java.sql.PreparedStatement ps;
	
    @Override
    public void init() throws ServletException {
        productDB = new ProductDB();
        conn=null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    	textStep(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	textStep(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    protected void textStep(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	Cart cart;
        HttpSession hts = request.getSession(true);
        if (hts.getAttribute("theShoppingCart") == null) {
            hts.setAttribute("theShoppingCart", new Cart());
        }
        cart = (Cart) hts.getAttribute("theShoppingCart");
        String action = request.getParameter("action");
        boolean itempage = false;
        String[] productList = request.getParameterValues("productList");
        switch (action) {
            case "updateCart":

                if (productList == null) {
                    request.getRequestDispatcher("/cart.jsp").forward(request, response);
                    return;
                }
                    if (request.getParameter("itempage")!= null && request.getParameter("itempage").equals("true")) {
                        itempage = true;
                    }
                for (String productCode : productList) {
                    Product product = productDB.getProduct(productCode);

                    String quantity = request.getParameter(productCode);
                    if (quantity == null) {
                        quantity = "1";
                    } else if (quantity.equals("") || !quantity.matches("[0-9]+")) {
                    } else if (Integer.parseInt(quantity) == 0) {
                        cart.removeItem(product);
                        hts.removeAttribute(productCode);
                    } else {
                        if (itempage) {
                            if (hts.getAttribute(productCode) == null) {
                                cart.addItem(product, 1);
                                hts.setAttribute(productCode, 1);

                            } else {
                                cart.addItem(product, (int) hts.getAttribute(productCode) + Integer.parseInt(quantity));
                                hts.setAttribute(productCode, (int) hts.getAttribute(productCode) + Integer.parseInt(quantity));
                            }
                        } else {
                            cart.addItem(product, Integer.parseInt(quantity));
                            hts.setAttribute(productCode, Integer.parseInt(quantity));
                        }
                    }
                }
                hts.setAttribute("theShoppingCart", cart);
                getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
                break;
            case "checkOut":
            	if(hts.getAttribute("firstName")==null){
            		hts.setAttribute("loginType","checkOut");
                    request.getRequestDispatcher("/checkoutChoice.jsp").forward(request, response);
                    return;
        		}
                UserDB userDB = new UserDB();
                hts.setAttribute("theUser", userDB.getUser((int)hts.getAttribute("userID")));
                User user = (User) hts.getAttribute("theUser");
                Order order = new Order();
                
                
                order.setDate(new Date());
                order.setUser(user.getFirstName());
                order.setUserID(user.getUserID());
                order.setOrderItem(cart.getItems());
                order.setTaxRate((float) 0.07);
                order.setTotalCost(cart.getCartAmount());
                
                
                
                hts.setAttribute("currentOrder", order);
                if(request.getParameter("fromPage")!=null && request.getParameter("fromPage").equals("order"))
                {
                	getServletContext().getRequestDispatcher("/payment.jsp").forward(request, response);
                	return;
                }
                else{
                	getServletContext().getRequestDispatcher("/order.jsp").forward(request, response);
                }
                break;
                
            case "confirmOrder":

            	try{
            		pool = ConnectionPool.getInstance();
                    conn = pool.getcConnection();
        			OrderDB orderDB = new OrderDB();
        			//Order orders =(Order)hts.getAttribute("currentOrder");
        			int res = orderDB.addOrder((Order)hts.getAttribute("currentOrder"));
        			if(res==1){
        				request.setAttribute("from","confirmOrder");
        				request.setAttribute("message","Payment Full");
        				User user1 = (User)hts.getAttribute("theUser");
        				System.out.println(user1.getEmail());
        				sendMail(request,user1.getEmail(), "Bestsell@gmail.com", "Order Conformation",true);
                        getServletContext().getRequestDispatcher("/order.jsp").forward(request, response);
                        return;
        			}
        			else{
        				System.out.println("some error");
        			}
            	}
            	catch (Exception e) {
            		e.printStackTrace();
            	}
            	break;
            
            case "viewOrders":
            	
            	if(hts.getAttribute("firstName")==null){
            		hts.setAttribute("loginType","viewOrders");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
        		}
            	else{
            		OrderDB orderDB=new OrderDB();
					ArrayList<Order> orders =orderDB.getOrders((int)hts.getAttribute("userID"));
					request.setAttribute("orders",orders);
					request.getRequestDispatcher("/ordersList.jsp").forward(request, response);
                    return;
               }

    		case "viewOrder":
    			if(hts.getAttribute("firstName")==null){
            		hts.setAttribute("loginType","viewOrder");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
        		}
            	else{
            		OrderDB orderDB=new OrderDB();
					Order orders =orderDB.getOrderItem(Integer.parseInt(request.getParameter("orderId")));
					request.setAttribute("orders",orders);
					request.getRequestDispatcher("/ordersListOrder.jsp").forward(request, response);
                    return;
               }
            	
        		}
        
	}

	private void sendMail(HttpServletRequest request,String to, String from, String subject,boolean bodyisHtml) {
		// TODO Auto-generated method stub
		String body;
		HttpSession hts = request.getSession();
		Order order = (Order) hts.getAttribute("currentOrder");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String sDate = sdf.format(date);
		User user = (User) hts.getAttribute("theUser");
		
		//creation of body for the mail
		body = "<p><span style=\"font-size: 30px; font: bold; font-weight: 300;\">Confirmation</span><br> "
						+"Date:"+sDate+"<br> <br> <u>ship to/bill to:</u><br>"+user.getFirstName()+"<br>"
						+user.getAddressOneField()+"<br>"
						+user.getAddressTwoField()+"<br> <br></p>"


						+" <table>"
						+"<tr>"
						+"	<td>Item</td>"
						+"	<td>Price</td>"
						+"	<td>Quantity</td>"
						+"	<td>Total</td>"
						+"</tr>"

						+"<tr>"
						+"	<td style=\"color: #00b7b7\"><hr></td>"
						+"	<td><hr></td>"
						+"	<td><hr></td>"
						+"	<td><hr></td>"
						+"</tr>";

						Cart cart = (Cart) request.getSession(true).getAttribute(
								"theShoppingCart");
						if (cart != null) {
							int i = 1;
							for (OrderItem item : cart.getItems()) {
								Product product = item.getProduct();
								body +="<tr>"
										+"<td>"+product.getProductName()+"</td>"
										+"<td>"+product.getPrice()+"</td>"
										+"<td>"+item.getQuantity()+"</td>"
										+"<td>"+item.getTotal()+"</td>"
										+"</tr>"
										+"";
							}
						}
						
						

						body+="<tr>"
						+"	<td style=\"color: #00b7b7\"><hr></td>"
						+"	<td><hr></td>"
						+"	<td><hr></td>"
						+"	<td><hr></td>"
						+"</tr>";
						
						body+="<tr>"
							  +"<td></td>"
							  +"<td></td>"
							  +"<td>subtotal</td>"
							  +"<td>"+cart.getCartAmount()+"</td>"
							  +"</tr>";
						
						body+="<tr>"
								  +"<td></td>"
								  +"<td></td>"
								  +"<td>tax</td>"
								  +"<td>"+order.getTaxRate() * cart.getCartAmount()+"</td>"
								  +"</tr>";
						
						body+="<tr>"
								  +"<td></td>"
								  +"<td></td>"
								  +"<td>total</td>"
								  +"<td>"+(cart.getCartAmount() + (order.getTaxRate() * cart.getCartAmount()))+"</td>"
								  +"</tr>";

						body+="</table>";
						
			//creation of body ended
			
						final String username = "Tiger.uncc@gmail.com";
				        final String password = "charlotte1771";
				        Properties props = new Properties();
				        props.put("mail.smtp.host", "smtp.gmail.com");
				        props.put("mail.smtp.socketFactory.port", "465");
				        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				        props.put("mail.smtp.auth", "true");
				        props.put("mail.smtp.port", "465");
				        Session session2;
				        session2 = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				            @Override
				            protected PasswordAuthentication getPasswordAuthentication() {
				                return new PasswordAuthentication(username, password);
				            }
				        });
					    try {
					    	

				            MimeMessage message = new MimeMessage(session2);
				            /*
				             * message.setFrom(new InternetAddress(from));
				             * message.addRecipient(Message.RecipientType.TO, new
				             * InternetAddress(to));
				             */

				            message.setFrom(new InternetAddress(from));
				            message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to));
				            message.setSubject(subject);
				            message.setText(body, "utf-8", "html");
				            Transport.send(message);
				        }
				        catch(Exception e){
				        	e.printStackTrace();
				        }				
	}

}
