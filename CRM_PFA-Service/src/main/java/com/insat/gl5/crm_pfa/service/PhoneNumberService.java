/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service;

import com.insat.gl5.crm_pfa.model.PhoneNumber;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public class PhoneNumberService extends GenericService{

    private static final String SUCCESS = " with success";
    /**
     * Save new PhoneNumber
     *
     * @param PhoneNumber 
     */
    public void savePhoneNumber(PhoneNumber PhoneNumber) throws Exception {

        try {
            persist(PhoneNumber);
            log.info("Create PhoneNumber " + getDisplayText(PhoneNumber) + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Creating PhoneNumber "+getDisplayText(PhoneNumber)+ " : -->", ex);
            throw ex;
        }
    }

    /**
     * Delete a PhoneNumber
     *
     * @param PhoneNumber 
     */
    public void deletePhoneNumber(PhoneNumber PhoneNumber) throws Exception {
        try {
            delete(PhoneNumber);
            log.info("Delete PhoneNumber " + getDisplayText(PhoneNumber)+ SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Deleting PhoneNumber "+getDisplayText(PhoneNumber)+" : -->", ex);
            throw ex;
        }
    }

    /**
     * Edit a PhoneNumber
     *
     * @param PhoneNumber 
     */
    public void editPhoneNumber(PhoneNumber PhoneNumber) throws Exception {

        try {
            edit(PhoneNumber);
            log.info("Update PhoneNumber " + getDisplayText(PhoneNumber) + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Updating PhoneNumber "+getDisplayText(PhoneNumber)+" : -->", ex);
            throw ex;
        }
    }

     /**
     * Get a list of all activities figured in database
     *
     * @return 
     */
    public List<PhoneNumber> getAllPhoneNumbers() {
        TypedQuery query = em.createQuery("SELECT p FROM PhoneNumber p", PhoneNumber.class);
        return query.getResultList();
    }
    
    private String getDisplayText(PhoneNumber PhoneNumber){
        return PhoneNumber.getType().getDisplayName() + " : " +PhoneNumber.getNumber();
    }
}
