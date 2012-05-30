/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.viewModel;

import com.insat.gl5.crm_pfa.model.PhoneNumber;

/**
 *
 * @author
 * Mu7ammed
 * 3li
 * --
 * mohamed.ali.affes@gmail.com
 * --
 */
public class PhoneNumberViewModel {
    private int index;
    private PhoneNumber phoneNumber;

    public PhoneNumberViewModel() {
    }

    
    public PhoneNumberViewModel(int index, PhoneNumber PhoneNumber) {
        this.index = index;
        this.phoneNumber = PhoneNumber;
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
     * @return the PhoneNumber
     */
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param PhoneNumber the PhoneNumber to set
     */
    public void setPhoneNumber(PhoneNumber PhoneNumber) {
        this.phoneNumber = PhoneNumber;
    }
    
}
