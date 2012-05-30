/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.viewModel;

import com.insat.gl5.crm_pfa.model.EmailAdress;

/**
 *
 * @author
 * Mu7ammed
 * 3li
 * --
 * mohamed.ali.affes@gmail.com
 * --
 */
public class EmailViewModel {
    private int index;
    private EmailAdress email;

    public EmailViewModel() {
    }

    
    public EmailViewModel(int index, EmailAdress email) {
        this.index = index;
        this.email = email;
    }

    
    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return the email
     */
    public EmailAdress getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(EmailAdress email) {
        this.email = email;
    }

}
