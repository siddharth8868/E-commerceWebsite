/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.ArrayList;

/**
 *
 * @author siddharth
 */
public class Cart {
    
    private ArrayList<OrderItem> orderitems;
    private float cartAmount;

    public Cart() {
        orderitems = new ArrayList<OrderItem>();
        cartAmount = 0;
    }
    
    
    
    public void addItem(Product product, int quantity){
        int i=0;
        //System.out.print("cart page: pp");
        boolean repeated = false;
        for (OrderItem orderitem : orderitems) {
            if(orderitem.getProduct().getProductCode().equals(product.getProductCode())){
                float prev = orderitem.getTotal();
                orderitem.setQuantity(quantity );
                float present = orderitem.getTotal();
                this.cartAmount = this.cartAmount -prev +present ;
                orderitems.set(i, orderitem);
                //System.out.print("");
                repeated = true;
                break;
            }
            i++;
        }
        if(!repeated){
            OrderItem orderitem=new OrderItem();
            orderitem.setProduct(product);
            orderitem.setQuantity(1);
            orderitems.add(orderitem);
            System.out.print(orderitem.getTotal());
            this.cartAmount = this.cartAmount + orderitem.getTotal();
        }
        
    }
    
    public void addItemDirect(Product product, int quantity){
     OrderItem orderitem=new OrderItem();
            orderitem.setProduct(product);
            orderitem.setQuantity(quantity);
            orderitems.add(orderitem);
            System.out.print(orderitem.getTotal());
            this.cartAmount = this.cartAmount + orderitem.getTotal();
        
    }
    
    
    public void removeItem(Product product){
        int i=0;
        for (OrderItem orderitem : orderitems) {
            if(orderitem.getProduct().getProductCode().equals(product.getProductCode())){
                this.cartAmount = this.cartAmount - orderitem.getTotal();
                orderitems.remove(orderitem);
                break;
            }
            i++;
        }
    }
    
    public ArrayList<OrderItem> getItems(){
        return orderitems;
    }
    
    public void emptyCart(){
        orderitems.clear();
    }
    
    public float getCartAmount(){
        return this.cartAmount;
    }
}
