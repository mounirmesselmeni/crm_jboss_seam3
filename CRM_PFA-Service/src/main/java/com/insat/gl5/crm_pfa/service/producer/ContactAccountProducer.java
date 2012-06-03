/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service.producer;

import com.insat.gl5.crm_pfa.model.Account;
import com.insat.gl5.crm_pfa.model.BackendUser;
import com.insat.gl5.crm_pfa.model.Contact;
import com.insat.gl5.crm_pfa.service.qualifier.CurrentUser;
import com.insat.gl5.crm_pfa.service.qualifier.DataRepository;
import java.util.List;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public class ContactAccountProducer {

    @Inject
    @DataRepository
    private EntityManager em;

    @Inject
    @CurrentUser
    private BackendUser backendUser;
    /**
     * Contacts list
     * @return 
     */
    @Produces
    @Named("lstContacts")
    public List<Contact> getLstContacts() {
        Query query = em.createQuery("select c from Contact c WHERE c.account.crmUser=?1").setParameter(1, backendUser);
        return query.getResultList();
    }
    
    /**
     * Accounts list
     * @return 
     */
    @Produces
    @Named("lstAccounts")
    public List<Account> getLstAccounts() {
        Query query = em.createQuery("select a from Account a WHERE a.crmUser=?1").setParameter(1, backendUser);
        return query.getResultList();
    }
}
