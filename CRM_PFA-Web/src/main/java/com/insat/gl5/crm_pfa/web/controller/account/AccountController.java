/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.account;

import com.insat.gl5.crm_pfa.enumeration.AddressType;
import com.insat.gl5.crm_pfa.enumeration.PhoneNumberType;
import com.insat.gl5.crm_pfa.model.Account;
import com.insat.gl5.crm_pfa.model.Address;
import com.insat.gl5.crm_pfa.model.PhoneNumber;
import com.insat.gl5.crm_pfa.service.AccountService;
import com.insat.gl5.crm_pfa.web.controller.ConversationController;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.seam.international.status.Messages;

/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Named
@ConversationScoped
public class AccountController extends ConversationController {

    @Inject
    private AccountService accountService;
    @Inject
    private Messages messages;
//    @Inject
    private Account account = new Account();
    private Address shippingAddress = new Address();
    private Address billingAddress = new Address();
    private PhoneNumber primaryPhoneNumber = new PhoneNumber(PhoneNumberType.TRAVAIL);
    private PhoneNumber secondaryPhoneNumber = new PhoneNumber(PhoneNumberType.TRAVAIL);
    private PhoneNumber fax = new PhoneNumber(PhoneNumberType.FAX);
    private String redirect;

    /**
     * Save an account
     * 
     */
    public String saveAccount() {

        try {
            
            getBillingAddress().setType(AddressType.TRAVAIL);
            getShippingAddress().setType(AddressType.TRAVAIL);
            
//            getAccount().setBillingAddress(getBillingAddress());
//            getAccount().setShippingAddress(getShippingAddress());
//            
//            getAccount().setPrimaryPhoneNumber(getPrimaryPhoneNumber());
//            getAccount().setSecondaryPhoneNumber(getSecondaryPhoneNumber());
//            
            accountService.saveAccount(getAccount());
            messages.info("Compte {0} est enregistré avec succés !", getAccount().getName());

            setAccount(null);

        } catch (Exception e) {
            messages.error("Erreur d'enregistrement du compte {0}", getAccount().getName());
            return null;
        }
        endConversation();
        return redirect;
    }

    /**
     * Edit an account
     * 
     */
    public String editAccount() {

        try {
            accountService.editAccount(getAccount());
            messages.info("Compte {0} est modifié avec succés !", getAccount().getName());

            setAccount(null);

        } catch (Exception e) {
            messages.error("Erreur de modification du compte {0}", getAccount().getName());
            return null;
        }
        endConversation();
        return redirect;
    }

    /**
     * Delete the selected account
     */
    public void deleteAccount() {

        try {
            accountService.deleteAccount(getAccount());
            messages.info("Compte {0} est supprimé avec succés !", getAccount().getName());

            setAccount(null);

        } catch (Exception e) {
            messages.error("Erreur de supression du compte {0}", getAccount().getName());
        }
        endConversation();
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
     * @return the redirect
     */
    public String getRedirect() {
        return redirect;
    }

    /**
     * @param redirect the redirect to set
     */
    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    /**
     * @return the primaryPhoneNumber
     */
    public PhoneNumber getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    /**
     * @param primaryPhoneNumber the primaryPhoneNumber to set
     */
    public void setPrimaryPhoneNumber(PhoneNumber primaryPhoneNumber) {
        this.primaryPhoneNumber = primaryPhoneNumber;
    }

    /**
     * @return the secondaryPhoneNumber
     */
    public PhoneNumber getSecondaryPhoneNumber() {
        return secondaryPhoneNumber;
    }

    /**
     * @param secondaryPhoneNumber the secondaryPhoneNumber to set
     */
    public void setSecondaryPhoneNumber(PhoneNumber secondaryPhoneNumber) {
        this.secondaryPhoneNumber = secondaryPhoneNumber;
    }

    /**
     * @return the fax
     */
    public PhoneNumber getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(PhoneNumber fax) {
        this.fax = fax;
    }
    
}
