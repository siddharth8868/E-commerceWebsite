/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author siddharth
 */
public class OrderItem {
    
    private Product product;
    private int quantity;

    public OrderItem() {
        //product;
        quantity=0;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public float getTotal() {
       return this.product.getPrice() * this.quantity;
    }
}
