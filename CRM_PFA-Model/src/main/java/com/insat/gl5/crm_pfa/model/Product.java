/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Min;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "select o from Product o"),
    @NamedQuery(name = "Product.findByProductId", query = "select o from Product o where id = :productId"),
    @NamedQuery(name = "Product.findByCategoryId", query = "select o from Product o where category.id = :catgoryId")})
public class Product extends BaseEntity {

    private String reference;
    private String name;
    private String description;
    private String image;
    @ManyToOne
    private Category category;
    private double price;
    @ManyToOne
    private TVA tva;
    @Min(0)
    private int quantity;

    public Product() {
    }

    public Product(String name, String description, String image, int quantity, Category category, String type, double price) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "name=" + name + '}';
    }

    /**
     * @return the reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference the reference to set
     */
    public void setReference(String reference) {
        this.reference = reference;
    }

    /**
     * @return the tva
     */
    public TVA getTva() {
        return tva;
    }

    /**
     * @param tva the tva to set
     */
    public void setTva(TVA tva) {
        this.tva = tva;
    }
}
