/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.domains;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Lovreone
 */

@Entity
@Table(name = "products")
public class Product implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int productId;
    
    @Column(name = "product_name")
    private String productName; // Zenfone 4 Max ZC520KL
    
    @Column(name = "unit_price")
    private int unitPrice;
    
    @Column(name = "stock_quantity")
    private int stockQuantity;
    // ...
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private ProductDetails productDetails; // OneToOne
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.product", cascade = CascadeType.ALL)
    private Set<OrderProduct> orderProducts = new HashSet<OrderProduct>(0); // ManyToMany

    public Product(String productName, int unitPrice, int stockQuantity) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
    }

    public Product() {
    }
    
    @Override
    public String toString() {
        return "PRODUCT: id: " + productId + ", productName: " + productName + ", unitPrice: " + unitPrice + ", stockQuantity: " + stockQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
    
}
