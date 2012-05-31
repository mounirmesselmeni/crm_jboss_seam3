/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service;

import com.insat.gl5.crm_pfa.model.ItemToPurchase;
import java.util.List;
import javax.persistence.TypedQuery;
/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public class ItemToPurchaseService extends GenericService{

    private static final String SUCCESS = " with success";
    
    /**
     * Save new ItemToPurchase
     *
     * @param ItemToPurchase 
     */
    public void saveItemToPurchase(ItemToPurchase ItemToPurchase) throws Exception {

        try {
            persist(ItemToPurchase);
            log.info("Create ItemToPurchase " + ItemToPurchase + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Creating ItemToPurchase "+ItemToPurchase +" : -->", ex);
            throw ex;
        }
    }

    /**
     * Delete an ItemToPurchase
     *
     * @param ItemToPurchase 
     */
    public void deleteItemToPurchase(ItemToPurchase ItemToPurchase) throws Exception {
        try {
            delete(ItemToPurchase);
            log.info("Delete ItemToPurchase " + ItemToPurchase  + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Deleting ItemToPurchase "+ItemToPurchase +" : -->", ex);
            throw ex;
        }
    }

    /**
     * Edit an ItemToPurchase
     *
     * @param ItemToPurchase 
     */
    public void editItemToPurchase(ItemToPurchase ItemToPurchase) throws Exception {

        try {
            edit(ItemToPurchase);
            log.info("Update ItemToPurchase " + ItemToPurchase  + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Updating ItemToPurchase "+ItemToPurchase +" : -->", ex);
            throw ex;
        }
    }

     /**
     * Get a list of all ItemToPurchases figured in database
     *
     * @return 
     */
    public List<ItemToPurchase> getAllItemToPurchases() {
        TypedQuery query = em.createQuery("SELECT a FROM ItemToPurchase a", ItemToPurchase.class);
        return query.getResultList();
    }

}
