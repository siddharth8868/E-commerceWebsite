/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import beans.Product;
import helper.ConnectionPool;
import helper.MyConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 *
 * @author siddharth
 */
public class ProductDB {

    ArrayList<Product> products;
    Connection conn;
    ConnectionPool pool;
    PreparedStatement ps;
    ResultSet rs;
    public ProductDB() {
        products = new ArrayList<Product>();
    }
    
    public void addProduct(String productCode, String productName, int categoryCode, String
    catalogCategory, float price, String description, String imageUrl){
        
        Product product = new Product();
        product.setProductCode(productCode);
        product.setProductName(productName);
        product.setProductCode(productCode);
        product.setCatalogCategory(catalogCategory);
        product.setPrice(price);
        product.setDescription(description);
        product.setGetImageURL(imageUrl);
        addProduct(product);
    }
    
    public void addProduct(Product product){
        try{
        pool = ConnectionPool.getInstance();
        conn = pool.getcConnection();
        //conn = pool.getcConnection();
        ps = conn.prepareStatement("insert into PRODUCT values(?,?,?,?,?,?)");
            ps.setString(1,product.getProductCode());
            ps.setString(2,product.getProductName());
            ps.setString(3,product.getCatalogCategory());
            ps.setFloat(4,product.getPrice());
            ps.setString(5,product.getDescription());
            ps.setString(6,product.getGetImageURL());
            
            int result = ps.executeUpdate();
            if(result == 1){
            System.out.println("success");
            }
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally{
        pool.freeConnection(conn);
        }
    }
    
    
    
    public Product getProduct(String productCode){
        Product product = null;
        try{
        pool = ConnectionPool.getInstance();
        conn = pool.getcConnection();
        //conn = MyConnection.getConnection();
        ps = conn.prepareStatement("select * from PRODUCT where productCode=?");
        ps.setString(1, productCode);            
        rs= ps.executeQuery();
        while(rs.next()){
            product = new Product();
            product.setProductCode(rs.getString(1));
            product.setProductName(rs.getString(2));
            product.setCatalogCategory(rs.getString(3));
            product.setPrice(rs.getFloat(4));
            product.setDescription(rs.getString(5));
            product.setGetImageURL(rs.getString(6));
        }
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally{
        pool.freeConnection(conn);
        }
        return product;
        
    }
    
    public ArrayList<Product> getProducts(){
        products.clear();
        try{
        pool = ConnectionPool.getInstance();
        conn = pool.getcConnection();
    	//conn = MyConnection.getConnection();
        ps = conn.prepareStatement("select * from PRODUCT");
            ResultSet rs = ps.executeQuery();
            Product product;
            while(rs.next()){
            product = new Product();
            product.setProductCode(rs.getString(1));
            product.setProductName(rs.getString(2));
            product.setCatalogCategory(rs.getString(3));
            product.setPrice(rs.getFloat(4));
            product.setDescription(rs.getString(5));
            product.setGetImageURL(rs.getString(6));
            products.add(product);
            }
        }
        catch(Exception e){
    		System.out.println("hello");
        e.printStackTrace();
        }
        finally{
        //pool.freeConnection(conn);
        }
        return products;
        
    }
    
    public ArrayList<Product> getProducts(String category){
        products.clear();
        try{
        pool = ConnectionPool.getInstance();
        conn = pool.getcConnection();
        //conn = MyConnection.getConnection();
        ps = conn.prepareStatement("SELECT * FROM PRODUCT WHERE catalogCategory=?");
        ps.setString(1,category);
            ResultSet rs = ps.executeQuery();
            Product product;
            while(rs.next()){
            product = new Product();
            product.setProductCode(rs.getString(1));
            product.setProductName(rs.getString(2));
            product.setCatalogCategory(rs.getString(3));
            product.setPrice(rs.getFloat(4));
            product.setDescription(rs.getString(5));
            product.setGetImageURL(rs.getString(6));
            products.add(product);
            }
        }
        catch(Exception e){
        e.printStackTrace();
        }
        finally{
            pool.freeConnection(conn);
        }
            return products;
    }
}
