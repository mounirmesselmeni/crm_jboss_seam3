/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import com.insat.gl5.crm_pfa.enumeration.CustomerType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Entity
@NamedQueries({@NamedQuery(name = "Account.findAll", query = "select o from Account o"), 
@NamedQuery(name = "Account.findByName", query = "select o from Account o where name = ?1")})
public class Account extends BaseEntity {

    private String name;
    @Enumerated(EnumType.STRING)
    private CustomerType type;
    private int nbEmployees;
    private String website;
    @Email
    @Column(unique = true)
    private String primaryEmail;
    @Email
    @Column(unique = true)
    private String secondaryEmail;
    @ManyToOne(cascade= CascadeType.PERSIST)
    private Address shippingAddress;
    @ManyToOne(cascade= CascadeType.PERSIST)
    private Address billingAddress;

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
     * @return the type
     */
    public CustomerType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(CustomerType type) {
        this.type = type;
    }

    /**
     * @return the nbEmployees
     */
    public int getNbEmployees() {
        return nbEmployees;
    }

    /**
     * @param nbEmployees the nbEmployees to set
     */
    public void setNbEmployees(int nbEmployees) {
        this.nbEmployees = nbEmployees;
    }

    /**
     * @return the primaryEmail
     */
    public String getPrimaryEmail() {
        return primaryEmail;
    }

    /**
     * @param primaryEmail the primaryEmail to set
     */
    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    /**
     * @return the secondaryEmail
     */
    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    /**
     * @param secondaryEmail the secondaryEmail to set
     */
    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    /**
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * @param website the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * @return the shippingAddress
     */
    public Address getShippingAddress() {
        return shippingAddress;
    }

    /**
     * @param shippingAddress the shippingAddress to set
     */
    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * @return the billingAddress
     */
    public Address getBillingAddress() {
        return billingAddress;
    }

    /**
     * @param billingAddress the billingAddress to set
     */
    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }
    
}
