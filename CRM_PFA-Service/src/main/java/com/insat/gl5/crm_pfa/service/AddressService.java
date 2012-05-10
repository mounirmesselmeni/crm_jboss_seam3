/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service;

import com.insat.gl5.crm_pfa.model.Address;
import java.util.List;
import javax.persistence.TypedQuery;
/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public class AddressService extends GenericService{

    private static final String SUCCESS = " with success";
    
    /**
     * Save new Address
     *
     * @param Address 
     */
    public void saveAddress(Address Address) throws Exception {

        try {
            persist(Address);
            log.info("Create Address " + getDisplayText(Address) + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Creating Address "+getDisplayText(Address) +" : -->", ex);
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
            log.info("Delete Address " + getDisplayText(Address)  + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Deleting Address "+getDisplayText(Address) +" : -->", ex);
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
            log.info("Update Address " + getDisplayText(Address)  + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Updating Address "+getDisplayText(Address) +" : -->", ex);
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
    
    private String getDisplayText(Address address){
        return address.getStreet()+", "+address.getPostalCode()+ " - "+ address.getCity();
    }
}
