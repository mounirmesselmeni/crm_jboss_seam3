/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import com.insat.gl5.crm_pfa.enumeration.OpportunityType;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Entity
public class Opportunity extends BaseEntity {

    private String name;
    private String description;
    private OpportunityType type;
    @Temporal(TemporalType.DATE)
    private Date closeDate;
    @ManyToOne
    @JoinColumn(name = "contactId")
    private Contact relatedTo;
    @ManyToOne
    @JoinColumn(name = "backendUserId")
    private BackendUser assignedTo;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemToPurchase> itemsToPurchase = new LinkedList<ItemToPurchase>();

    public double getTotalFidelityPrice() {
        double total = getTotalPrice() * getRelatedTo().getAccount().getFidelity().getScore();
        return total;
    }

    public double getTotalPrice() {
        double total = 0;
        for (ItemToPurchase itemToPurchase : itemsToPurchase) {
            total += itemToPurchase.getProduct().getPrice() * itemToPurchase.getQuantity();
        }
        return total;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the closeDate
     */
    public Date getCloseDate() {
        return closeDate;
    }

    /**
     * @param closeDate the closeDate to set
     */
    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    /**
     * @return the type
     */
    public OpportunityType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(OpportunityType type) {
        this.type = type;
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


    @Override
    public String toString() {
        return name ;
    }

    /**
     * @return the relatedTo
     */
    public Contact getRelatedTo() {
        return relatedTo;
    }

    /**
     * @param relatedTo the relatedTo to set
     */
    public void setRelatedTo(Contact relatedTo) {
        this.relatedTo = relatedTo;
    }

    /**
     * @return the assignedTo
     */
    public BackendUser getAssignedTo() {
        return assignedTo;
    }

    /**
     * @param assignedTo the assignedTo to set
     */
    public void setAssignedTo(BackendUser assignedTo) {
        this.assignedTo = assignedTo;
    }
    
}
