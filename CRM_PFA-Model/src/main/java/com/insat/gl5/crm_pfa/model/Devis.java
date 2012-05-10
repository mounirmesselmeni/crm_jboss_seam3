/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Entity
public class Devis extends BaseEntity {

    @OneToMany
    private Collection<LineItem> lineItems;
    @ManyToOne
    private Contact contact;
    private Timestamp creationDate;
    private double totalPrice;

    public Collection<LineItem> getLineItems() {
        return lineItems;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public void setLineItems(Collection<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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
        if (!(object instanceof Devis)) {
            return false;
        }
        Devis other = (Devis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

   

    public void addPurchase(LineItem item, int quantity, double price) {
        if (lineItems == null) {
            lineItems = new ArrayList<LineItem>();
        }
        //item.setOrder(this);
        item.setQuantity(item.getQuantity() + quantity);
        item.setSubTotal(item.getSubTotal() + (quantity * price));
        totalPrice += item.getSubTotal() + (quantity * price);
    }

    public void updatePurchaseAndUpdateLineItem(LineItem item, int quantity, double price) {
        if (lineItems == null) {
            lineItems = new ArrayList<LineItem>();
        }
        int oldQuantity = item.getQuantity();
        item.setQuantity(quantity);
        item.setSubTotal(quantity * price);
        totalPrice = totalPrice - (oldQuantity * price);
        totalPrice += quantity * price;
    }
    Long itemId = new Long(0);

    public LineItem addPurchaseAndLineItem(Product product, int quantity, double price) {

        if (lineItems == null || lineItems.isEmpty()) {
            lineItems = new ArrayList<LineItem>();
        }
        LineItem item = new LineItem();
        item.setId(itemId);
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setSubTotal(quantity * price);
        lineItems.add(item);
        totalPrice += quantity * price;
        itemId = itemId + 1;
        return item;
    }

    /**
     * @return the contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
