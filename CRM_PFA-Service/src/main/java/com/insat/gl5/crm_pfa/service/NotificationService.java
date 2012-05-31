/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service;

import com.insat.gl5.crm_pfa.model.BackendUser;
import com.insat.gl5.crm_pfa.model.Contact;
import com.insat.gl5.crm_pfa.model.Notification;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public class NotificationService extends GenericService{

    private static final String SUCCESS = " with success";
    /**
     * Save new Notification
     *
     * @param notification 
     */
    public void saveNotification(Notification notification) throws Exception {

        try {
            persist(notification);
            log.info("Create Notification " + getDisplayText(notification) + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Creating Notification "+getDisplayText(notification)+ " : -->", ex);
            throw ex;
        }
    }

    /**
     * Delete an Notification
     *
     * @param Notification 
     */
    public void deleteNotification(Notification notification) throws Exception {
        try {
            delete(notification);
            log.info("Delete Notification " + getDisplayText(notification)+ SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Deleting Notification "+getDisplayText(notification)+" : -->", ex);
            throw ex;
        }
    }

    /**
     * Edit an Notification
     *
     * @param Notification 
     */
    public void editNotification(Notification notification) throws Exception {

        try {
            edit(notification);
            log.info("Update Notification " + getDisplayText(notification) + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Updating Notification "+getDisplayText(notification)+" : -->", ex);
            throw ex;
        }
    }

     /**
     * Get a list of notifications
     *
     * @return 
     */
    public List<Notification> getAllNotificationsByContact(Contact contact) {
        TypedQuery query = em.createQuery("SELECT nc.notification FROM NotificationContact nc WHERE nc.contact=?1 AND nc.notification.readed=false", Notification.class).setParameter(1, contact);
        return query.getResultList();
    }
     /**
     * Get a list of notifications
     *
     * @return 
     */
    public List<Notification> getAllNotificationsByBackendUser(BackendUser backendUser) {
        TypedQuery query = em.createQuery("SELECT nc.notification FROM NotificationContact nc WHERE nc.backendUser=?1 AND nc.notification.readed=false", Notification.class).setParameter(1, backendUser);
        return query.getResultList();
    }
    
    private String getDisplayText(Notification notification){
        return notification.getType().getDisplayName()+" : "+notification.getContent() ;
    }
}
