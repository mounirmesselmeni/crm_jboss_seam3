/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Entity
public class Devis extends BaseEntity {

    @OneToMany(cascade= CascadeType.ALL)
    private List<ItemToPurchase> itemsToPurchase = new LinkedList<ItemToPurchase>();
    @ManyToOne
    private Contact contact;

    public double getTotalPrice() {
        double total = 0;
        for (ItemToPurchase itemToPurchase : itemsToPurchase) {
            total += itemToPurchase.getProduct().getPrice() * itemToPurchase.getQuantity();
        }
        return total;
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

    /**
     * @return the itemsToPurchase
     */
    public List<ItemToPurchase> getItemsToPurchase() {
        return itemsToPurchase;
    }

    /**
     * @param itemsToPurchase the itemsToPurchase to set
     */
    public void setItemsToPurchase(List<ItemToPurchase> itemsToPurchase) {
        this.itemsToPurchase = itemsToPurchase;
    }
}
