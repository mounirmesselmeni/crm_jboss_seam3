/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author
 * Mu7ammed
 * 3li
 * --
 * mohamed.ali.affes@gmail.com
 * --
 */
@Entity
public class ActivationCode extends BaseEntity{
    
    private String code;
    @OneToOne
    private Contact contact;

    public ActivationCode() {
    }

    public ActivationCode(Contact contact) {
        this.contact = contact;
        this.code = java.util.UUID.randomUUID().toString();
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ActivationCode other = (ActivationCode) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        if (this.contact != other.contact && (this.contact == null || !this.contact.equals(other.contact))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.code != null ? this.code.hashCode() : 0);
        hash = 79 * hash + (this.contact != null ? this.contact.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Code=" + code + ", contact=" + contact + '.';
    }
    
}
