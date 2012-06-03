/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service;

import com.insat.gl5.crm_pfa.model.Contact;
import com.insat.gl5.crm_pfa.model.Opportunity;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public class OpportunityService extends GenericService{

    private static final String SUCCESS = " with success";
    /**
     * Save new Opportunity
     *
     * @param Opportunity 
     */
    public void saveOpportunity(Opportunity Opportunity) throws Exception {

        try {
            persist(Opportunity);
            log.info("Create Opportunity " + getDisplayText(Opportunity) + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Creating Opportunity "+getDisplayText(Opportunity)+ " : -->", ex);
            throw ex;
        }
    }

    /**
     * Delete an Opportunity
     *
     * @param Opportunity 
     */
    public void deleteOpportunity(Opportunity Opportunity) throws Exception {
        try {
            delete(Opportunity);
            log.info("Delete Opportunity " + getDisplayText(Opportunity)+ SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Deleting Opportunity "+getDisplayText(Opportunity)+" : -->", ex);
            throw ex;
        }
    }

    /**
     * Edit an Opportunity
     *
     * @param Opportunity 
     */
    public void editOpportunity(Opportunity Opportunity) throws Exception {

        try {
            edit(Opportunity);
            log.info("Update Opportunity " + getDisplayText(Opportunity) + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Updating Opportunity "+getDisplayText(Opportunity)+" : -->", ex);
            throw ex;
        }
    }

     /**
     * Get a list of all opportunities figured in database
     *
     * @return 
     */
    public List<Opportunity> getAllOpportunitys() {
        TypedQuery query = em.createQuery("SELECT a FROM Opportunity a", Opportunity.class);
        return query.getResultList();
    }
    public List<Opportunity> getOpportunitiesByContact(Contact contact) {
        TypedQuery query = em.createQuery("SELECT a FROM Opportunity a WHERE a.relatedTo=?1", Opportunity.class).setParameter(1, contact);
        return query.getResultList();
    }
    public void deleteOpportunitiesByContact(Contact contact) {
        Query query = em.createQuery("DELETE FROM Opportunity a WHERE a.relatedTo=?1").setParameter(1, contact);
        int result = query.executeUpdate();
    }
    
    private String getDisplayText(Opportunity Opportunity){
        return Opportunity.getType().getDisplayName()+" : "+Opportunity.getName() ;
    }
    
    public Opportunity findById(Long id){
        return em.find(Opportunity.class, id);
    }
}
