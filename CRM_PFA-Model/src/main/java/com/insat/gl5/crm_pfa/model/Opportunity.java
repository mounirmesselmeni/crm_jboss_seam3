/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import com.insat.gl5.crm_pfa.enumeration.OpportunityType;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    private int probability;
    private int amount;
    @ManyToOne
    @JoinColumn(name="accountId")
    private Account account;

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
     * @return the probability
     */
    public int getProbability() {
        return probability;
    }

    /**
     * @param probability the probability to set
     */
    public void setProbability(int probability) {
        this.probability = probability;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @return the account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(Account account) {
        this.account = account;
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
}
