/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import beans.User;
import helper.MyConnection;
import helper.ConnectionPool;
import helper.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author siddharth
 */
public class UserDB {
    
    ArrayList<User> users;
    static ConnectionPool pool;
    static Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    public UserDB(){
        users=new ArrayList<>();   
    }
    
    
    public static int addUser(User user){
    	int res = 0;
        try{
        	pool = ConnectionPool.getInstance();
            conn = pool.getcConnection();
        	PreparedStatement ps = conn.prepareStatement("INSERT INTO USER(firstName,lastName,emailAddr,address1Field,address2Field,city,state,zipPostalCode,country,pass,role,salt) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1,user.getFirstName());
			ps.setString(2,user.getLastName());
			ps.setString(3,user.getEmail());
			ps.setString(4,user.getAddressOneField());
			ps.setString(5,user.getAddressTwoField());
			ps.setString(6,user.getCity());
			ps.setString(7,user.getState());
			ps.setString(8,user.getPostCode());
			ps.setString(9,user.getCountry());
			ps.setString(11,user.getRole());
			
			String salt = PasswordUtil.getSalt();
			String password = user.getPassword() + salt;
			
			ps.setString(10,PasswordUtil.hashPassword(password));
			ps.setString(12,salt);
			
			
			res = ps.executeUpdate();
            return res;
            
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally{
        pool.freeConnection(conn);
        }
		return res;
        
    }
    
    public User getUser(int userID){
        User user =null;
        try{
         pool = ConnectionPool.getInstance();
         conn = pool.getcConnection();
         //conn = MyConnection.getConnection();
        ps = conn.prepareStatement("SELECT * FROM USER WHERE userID=?");
            ps.setInt(1,userID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            user = new User();
            user.setUserID(rs.getInt(1));
            user.setFirstName(rs.getString(2));
            user.setLastName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setAddressOneField(rs.getString(5));
            user.setAddressTwoField(rs.getString(6));
            user.setCity(rs.getString(7));
            user.setState(rs.getString(8));
            user.setPostCode(rs.getString(9));
            user.setCountry(rs.getString(10));
            user.setPassword(rs.getString(11));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
        pool.freeConnection(conn);
        }
        
        return user;
    }
    
    public ArrayList<User> getUsers(){
        users.clear();
        try{
         pool = ConnectionPool.getInstance();
         conn = pool.getcConnection();
         //conn = MyConnection.getConnection();
         ps = conn.prepareStatement("SELECT * FROM USER");
            ResultSet rs = ps.executeQuery();
            User user;
            while(rs.next()){
            user = new User();
            user.setUserID(rs.getInt(1));
            user.setFirstName(rs.getString(2));
            user.setLastName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setAddressOneField(rs.getString(5));
            user.setAddressTwoField(rs.getString(6));
            user.setCity(rs.getString(7));
            user.setState(rs.getString(8));
            user.setPostCode(rs.getString(9));
            user.setCountry(rs.getString(10));
            user.setPassword(rs.getString(11));
            
            users.add(user);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
        pool.freeConnection(conn);
        }
        
        return users;
    }
}
