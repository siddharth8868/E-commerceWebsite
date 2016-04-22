/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import java.io.Serializable;
import java.util.*;

public class Order implements Serializable{
    
private int orderNumber;
private Date date;
private String user;
private int userID;
private ArrayList<OrderItem> orderItems;    
private float taxRate;
private float totalCost;

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }
private boolean paid;

    public Order() {
        orderNumber=0;
        date=null;
        user="";
        orderItems=new ArrayList<OrderItem>();
        taxRate=0;
        totalCost=0;
       paid=false;
    }
    
    public int getOrderNumber() {
        return orderNumber;
    }

    public Date getDate() {
        return date;
    }

    public String getUser() {
        return user;
    }
    
    public int getUserID() {
    	return userID;
    }

    public ArrayList<OrderItem> getOrderItem() {
        return orderItems;
    }

    public float getTaxRate() {
        return taxRate;
    }

    public boolean getPaid() {
        return paid;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    public void setUserID(int userID) {
    	this.userID = userID;
    }

    public void setOrderItem(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setTaxRate(float taxRate) {
        this.taxRate = taxRate;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

}
    

    

