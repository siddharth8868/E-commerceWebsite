/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author siddharth
 */
public class MyConnection {

    static Connection conn;
    
    public static Connection getConnection(){
        try{
            String url="jdbc:mysql://localhost:3306/NBAD";
            String username="root";
            String password="password";
            Class.forName("com.mysql.jdbc.Driver");
            //conn = DriverManager.getConnection(url,username,password);
            conn = DriverManager.getConnection("jdbc:mysql://127.7.77.130:3306/NBAD","admin3tBmwE1","V394Hn1nYGYv");
            return conn;
        }
        catch(Exception e){
            //e.printStackTrace();
        	System.out.println("error by connection "+ e.getMessage());
            return conn;
        }
        
    }
    
    
}
