/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.domains;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Lovreone
 */

@Entity
@Table(name = "order_product")
@AssociationOverrides({
		@AssociationOverride(name = "pk.product",
			joinColumns = @JoinColumn(name = "product_id")),
		@AssociationOverride(name = "pk.order",
			joinColumns = @JoinColumn(name = "order_id")) })
public class OrderProduct implements Serializable {
    
    @EmbeddedId
    private OrderProductId pk = new OrderProductId();
    
    @Column(name = "product_qty")
    private int productQty;

    public OrderProduct(Order order, Product product, int productQty) {
        this.pk.setOrder(order);
        this.pk.setProduct(product);
        this.productQty = productQty;
    }
  
    public OrderProduct() {
    }
    
    @Override
    public String toString() {
        return "OrderProduct id: " + pk + ", productQty: " + productQty;
    }

    public OrderProductId getPk() {
        return pk;
    }

    public void setPk(OrderProductId pk) {
        this.pk = pk;
    }
    
    @Transient
    public Product getProduct() {
        return getPk().getProduct();
    }

    public void setProduct(Product product) {
        getPk().setProduct(product);
    }
    
    @Transient
    public Order getOrder() {
        return getPk().getOrder();
    }

    public void setOrder(Order order) {
        getPk().setOrder(order);
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }
    
    // Below methods taken from mkyong MxN+1 tutorial
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        OrderProduct that = (OrderProduct) o;

        if (getPk() != null ? !getPk().equals(that.getPk())
                : that.getPk() != null)
            return false;

        return true;
    }

    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }
    
}
