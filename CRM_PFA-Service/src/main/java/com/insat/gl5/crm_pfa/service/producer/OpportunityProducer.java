/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service.producer;

import com.insat.gl5.crm_pfa.model.Account;
import com.insat.gl5.crm_pfa.model.Opportunity;
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
public class OpportunityProducer {

    @Inject
    @DataRepository
    private EntityManager em;

    /**
     * Opportunities list
     * @return 
     */
    @Produces
    @Named("lstOpportunities")
    public List<Opportunity> getLstContacts() {
        Query query = em.createQuery("select o from Opportunity o");
        return query.getResultList();
    }
    
  
}
