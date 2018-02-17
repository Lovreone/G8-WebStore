/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.domains;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Lovreone
 */

@Entity
@Table(name = "product_details")
public class ProductDetails implements Serializable {
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "img_location")
    private String imagePath;
    
    /* POTENTIAL ADDITIONAL FIELDS TO ADD IN FUTURE PHASES
    private String dimensions; // 150.5 x 73.3 x 8.8 mm
    private String weight; // 156 grams
    private String processor; // Quad-core Cortex-A53, 1400 MHz
    private String ramMemory; // 3072 MB
    private String internalMemory; // 32768 MB
    private String batteryCapacity;  // 4100 mAh
    private String resolution;  // 720x1280 pixels 
    private String size; // 5.2 inča
    private String backCamera; // 8 mpx
    private String frontCamera; // 5 mpx
    private String operatingSystem; // Android v7.0
    private String otherDetails; // dual-SIM, vibration, kompas, akcelerometar, čitač otiska prsta,
    */
    
    @Id
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductDetails(String description, String imagePath) {
        this.description = description;
        this.imagePath = imagePath;
    }

    public ProductDetails() {
    }
    
    @Override
    public String toString() {
        return "PRODUCT-DETAILS: id: " + product.getProductId() + ", description: " + description + ", imagePath: " + imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
      
}
