/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service;

import com.insat.gl5.crm_pfa.model.Account;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public class AccountService extends GenericService{

    private static final String SUCCESS = " with success";

    /**
     * Save new account
     *
     * @param account 
     */
    public void saveAccount(Account account) throws Exception {

        try {
            persist(account);
            log.info("Create Account " + account.getName() + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Creating Account "+account.getName()+" : -->", ex);
            throw ex;
        }
    }

    /**
     * Delete an Account
     *
     * @param account 
     */
    public void deleteAccount(Account account) throws Exception {
        try {
            delete(account);
            log.info("Delete Account " + account.getName() + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Deleting Account "+account.getName()+" : -->", ex);
            throw ex;
        }
    }

    /**
     * Edit an Account
     *
     * @param account 
     */
    public void editAccount(Account account) throws Exception {

        try {
            edit(account);
            log.info("Update Account " + account.getName() + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Updating Account "+account.getName()+" : -->", ex);
            throw ex;
        }
    }

     /**
     * Get a list of all accounts figured in database
     *
     * @return 
     */
    public List<Account> getAllAccounts() {
        TypedQuery query = em.createQuery("SELECT a FROM Account a", Account.class);
        return query.getResultList();
    }
    
    public List<Account> getFiltredAccounts(String name){
        TypedQuery query = em.createQuery("SELECT a FROM Account a WHERE a.name LIKE '%"+name+"'", Account.class);
        return query.getResultList();
    }
}
