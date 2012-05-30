/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
@Entity
public class BackendUser extends BaseEntity {

    private String login;
    @OneToOne
    @JoinColumn(name = "emailId")
    private EmailAdress emailAdress;

    /**
     * @return the emailAdress
     */
    public EmailAdress getEmailAdress() {
        return emailAdress;
    }

    /**
     * @param emailAdress the emailAdress to set
     */
    public void setEmailAdress(EmailAdress emailAdress) {
        this.emailAdress = emailAdress;
    }

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
}
