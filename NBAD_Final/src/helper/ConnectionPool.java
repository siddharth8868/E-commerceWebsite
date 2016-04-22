/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author siddharth
 */
public class ConnectionPool {
    
    private static ConnectionPool pool = null;
    private static DataSource dataSourse = null;
    
    private ConnectionPool(){
        try{
            InitialContext ic = new InitialContext();
            dataSourse = (DataSource) ic.lookup("java:/comp/env/jdbc/NBAD");
        }
        catch(Exception e){
            System.out.println("ss"+e);
        }
    }
    
    public static synchronized ConnectionPool getInstance(){
        if(pool == null){
            pool = new ConnectionPool();
        }
        return pool;
    }
    
    public Connection getcConnection(){
        try{
            return dataSourse.getConnection();
        }
        catch(Exception e){
             System.out.println(e);
             return null;
        }
    }
    
    public void freeConnection(Connection c){
        try{
            c.close();
        }
        catch(Exception e){
             System.out.println(e);
        }
    }
        
}
