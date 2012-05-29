/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.account;

import com.insat.gl5.crm_pfa.model.Account;
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
    private String redirect;

    /**
     * Save an account
     * 
     */
    public String saveAccount() {

        try {
            
//            getBillingAddress().setType(AddressType.TRAVAIL);
//            getShippingAddress().setType(AddressType.TRAVAIL);
//            
//            getAccount().setBillingAddress(getBillingAddress());
//            getAccount().setShippingAddress(getShippingAddress());
//            
//            getAccount().setPrimaryPhoneNumber(getPrimaryPhoneNumber());
//            getAccount().setSecondaryPhoneNumber(getSecondaryPhoneNumber());
            
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

   
}
