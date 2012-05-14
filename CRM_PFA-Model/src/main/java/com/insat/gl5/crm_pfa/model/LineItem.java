/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Entity
public class LineItem extends BaseEntity {

    private double subTotal;
    private int quantity;
    @ManyToOne
    private Product product;
    
    @ManyToOne
    private Opportunity opportunity;

    public LineItem() {
    }

    public LineItem(double subTotal, int quantity, Product product) {
        this.product = product;
        this.quantity = quantity;
        this.subTotal = subTotal;
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

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
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
        if (!(object instanceof LineItem)) {
            return false;
        }
        LineItem other = (LineItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LineItem{" + "quantity=" + quantity + ", product=" + product + '}';
    }

    /**
     * @return the opportunity
     */
    public Opportunity getOpportunity() {
        return opportunity;
    }

    /**
     * @param opportunity the opportunity to set
     */
    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }

    
}
