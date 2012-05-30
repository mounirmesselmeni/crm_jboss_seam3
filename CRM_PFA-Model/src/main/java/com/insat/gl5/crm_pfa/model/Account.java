/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import com.insat.gl5.crm_pfa.enumeration.CustomerType;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "select o from Account o"),
    @NamedQuery(name = "Account.findByName", query = "select o from Account o where name = ?1")})
public class Account extends BaseEntity {

    private String name;
    @Enumerated(EnumType.STRING)
    private CustomerType type;
    private String website;
    private String logoURL;
    @OneToMany(cascade = CascadeType.ALL)
    private List<EmailAdress> lstEmails = new LinkedList<EmailAdress>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> lstAddresses = new LinkedList<Address>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<PhoneNumber> lstPhoneNumbers = new LinkedList<PhoneNumber>();
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "fidelityId")
    private Fidelity fidelity;

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
     * @return the lstEmails
     */
    public List<EmailAdress> getLstEmails() {
        return lstEmails;
    }

    /**
     * @param lstEmails the lstEmails to set
     */
    public void setLstEmails(List<EmailAdress> lstEmails) {
        this.lstEmails = lstEmails;
    }

    /**
     * @return the lstPhoneNumbers
     */
    public List<PhoneNumber> getLstPhoneNumbers() {
        return lstPhoneNumbers;
    }

    /**
     * @param lstPhoneNumbers the lstPhoneNumbers to set
     */
    public void setLstPhoneNumbers(List<PhoneNumber> lstPhoneNumbers) {
        this.lstPhoneNumbers = lstPhoneNumbers;
    }

    /**
     * @return the lstAddresses
     */
    public List<Address> getLstAddresses() {
        return lstAddresses;
    }

    /**
     * @param lstAddresses the lstAddresses to set
     */
    public void setLstAddresses(List<Address> lstAddresses) {
        this.lstAddresses = lstAddresses;
    }

    /**
     * @return the fidelity
     */
    public Fidelity getFidelity() {
        return fidelity;
    }

    /**
     * @param fidelity the fidelity to set
     */
    public void setFidelity(Fidelity fidelity) {
        this.fidelity = fidelity;
    }

    /**
     * @return the logoURL
     */
    public String getLogoURL() {
        return logoURL;
    }

    /**
     * @param logoURL the logoURL to set
     */
    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }
}
