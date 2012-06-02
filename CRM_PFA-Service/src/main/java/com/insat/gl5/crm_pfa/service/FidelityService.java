/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service;

import com.insat.gl5.crm_pfa.model.Fidelity;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public class FidelityService extends GenericService{

    private static final String SUCCESS = " with success";
    
    /**
     * Save new Fidelity
     *
     * @param Fidelity 
     */
    public void saveFidelity(Fidelity Fidelity) throws Exception {

        try {
            persist(Fidelity);
            log.info("Create Fidelity " + getDisplayText(Fidelity) + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Creating Fidelity "+getDisplayText(Fidelity)+ " : -->", ex);
            throw ex;
        }
    }

    /**
     * Delete a Fidelity
     *
     * @param Fidelity 
     */
    public void deleteFidelity(Fidelity Fidelity) throws Exception {
        try {
            delete(Fidelity);
            log.info("Delete Fidelity " + getDisplayText(Fidelity)+ SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Deleting Fidelity "+getDisplayText(Fidelity)+" : -->", ex);
            throw ex;
        }
    }

    /**
     * Edit a Fidelity
     *
     * @param Fidelity 
     */
    public void editFidelity(Fidelity Fidelity) throws Exception {

        try {
            edit(Fidelity);
            log.info("Update Fidelity " + getDisplayText(Fidelity) + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Updating Fidelity "+getDisplayText(Fidelity)+" : -->", ex);
            throw ex;
        }
    }

     /**
     * Get a list of all fidelities figured in database
     *
     * @return 
     */
    public List<Fidelity> getAllFidelitys() {
        TypedQuery query = em.createQuery("SELECT f FROM Fidelity f", Fidelity.class);
        return query.getResultList();
    }
 
    private String getDisplayText(Fidelity  fidelity){
        return ""+fidelity.getScore();
    }
}
