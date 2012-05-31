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
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CrmUser extends BaseEntity {

    private String login;
    @OneToMany(cascade = CascadeType.ALL)
    private List<EmailAdress> lstEmails = new LinkedList<EmailAdress>();

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
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
}
