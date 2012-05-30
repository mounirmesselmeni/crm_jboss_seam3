/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service;

import com.insat.gl5.crm_pfa.model.Address;
import com.insat.gl5.crm_pfa.model.EmailAdress;
import com.insat.gl5.crm_pfa.model.PhoneNumber;
import java.util.List;
import javax.persistence.TypedQuery;
/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public class CoordinatesService extends GenericService{

    private static final String SUCCESS = " with success";
    
    /**
     * Save new EmailAdress
     *
     * @param EmailAdress 
     */
    public void saveEmailAdress(EmailAdress EmailAdress) throws Exception {

        try {
            persist(EmailAdress);
            log.info("Create EmailAdress " + EmailAdress + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Creating EmailAdress "+EmailAdress +" : -->", ex);
            throw ex;
        }
    }

    /**
     * Delete an EmailAdress
     *
     * @param EmailAdress 
     */
    public void deleteEmailAdress(EmailAdress EmailAdress) throws Exception {
        try {
            delete(EmailAdress);
            log.info("Delete EmailAdress " + EmailAdress  + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Deleting EmailAdress "+EmailAdress +" : -->", ex);
            throw ex;
        }
    }

    /**
     * Edit an EmailAdress
     *
     * @param EmailAdress 
     */
    public void editEmailAdress(EmailAdress EmailAdress) throws Exception {

        try {
            edit(EmailAdress);
            log.info("Update EmailAdress " + EmailAdress  + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Updating EmailAdress "+EmailAdress +" : -->", ex);
            throw ex;
        }
    }

     /**
     * Get a list of all EmailAdresss figured in database
     *
     * @return 
     */
    public List<EmailAdress> getAllEmailAdresss() {
        TypedQuery query = em.createQuery("SELECT a FROM EmailAdress a", EmailAdress.class);
        return query.getResultList();
    }
     /**
     * Save new Address
     *
     * @param Address 
     */
    public void saveAddress(Address Address) throws Exception {

        try {
            persist(Address);
            log.info("Create Address " + Address + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Creating Address "+Address +" : -->", ex);
            throw ex;
        }
    }

    /**
     * Delete an Address
     *
     * @param Address 
     */
    public void deleteAddress(Address Address) throws Exception {
        try {
            delete(Address);
            log.info("Delete Address " + Address  + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Deleting Address "+ Address +" : -->", ex);
            throw ex;
        }
    }

    /**
     * Edit an Address
     *
     * @param Address 
     */
    public void editAddress(Address Address) throws Exception {

        try {
            edit(Address);
            log.info("Update Address " + Address  + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Updating Address "+ Address +" : -->", ex);
            throw ex;
        }
    }

     /**
     * Get a list of all Addresss figured in database
     *
     * @return 
     */
    public List<Address> getAllAddresss() {
        TypedQuery query = em.createQuery("SELECT a FROM Address a", Address.class);
        return query.getResultList();
    }
    
      /**
     * Save new PhoneNumber
     *
     * @param PhoneNumber 
     */
    public void savePhoneNumber(PhoneNumber PhoneNumber) throws Exception {

        try {
            persist(PhoneNumber);
            log.info("Create PhoneNumber " + PhoneNumber + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Creating PhoneNumber "+ PhoneNumber+ " : -->", ex);
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
            log.info("Delete PhoneNumber " + PhoneNumber + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Deleting PhoneNumber "+ PhoneNumber +" : -->", ex);
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
            log.info("Update PhoneNumber " + PhoneNumber + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Updating PhoneNumber "+ PhoneNumber +" : -->", ex);
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
}
