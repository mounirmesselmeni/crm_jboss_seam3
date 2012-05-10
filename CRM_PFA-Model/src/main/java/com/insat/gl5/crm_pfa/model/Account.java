/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import com.insat.gl5.crm_pfa.enumeration.CustomerType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Entity
public class Account extends BaseEntity {

    private String name;
    private CustomerType type;
    private List<Contact> contacts = new ArrayList<Contact>();
    private List<Address> addresses = new ArrayList<Address>();
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
     * @return the contacts
     */
    @OneToMany(mappedBy = "account")
    public List<Contact> getContacts() {
        return contacts;
    }

    /**
     * @param contacts the contacts to set
     */
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * @return the addresses
     */
    @OneToMany(mappedBy = "account")
    public List<Address> getAddresses() {
        return addresses;
    }

    /**
     * @param addresses the addresses to set
     */
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
