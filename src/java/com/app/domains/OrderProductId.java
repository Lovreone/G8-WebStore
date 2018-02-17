/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.domains;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * @author Lovreone
 */

@Embeddable
public class OrderProductId implements Serializable {
    
    @ManyToOne
    private Product product;
    
    @ManyToOne
    private Order order;
    
    @Override
    public String toString() {
        return "( OrderProductId product: " + product.getProductId() + ", order: " + order.getOrderId() + " )";
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
    // Below methods taken from mkyong MxN+1 tutorial
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderProductId that = (OrderProductId) o;

        if (product != null ? !product.equals(that.product) : that.product != null) return false;
        if (order != null ? !order.equals(that.order) : that.order != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (product != null ? product.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }
        
}
