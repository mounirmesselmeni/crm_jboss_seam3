/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller;

import com.insat.gl5.crm_pfa.model.BackendUser;
import com.insat.gl5.crm_pfa.model.Contact;
import com.insat.gl5.crm_pfa.model.Notification;
import com.insat.gl5.crm_pfa.service.NotificationService;
import com.insat.gl5.crm_pfa.service.qualifier.CurrentContact;
import com.insat.gl5.crm_pfa.service.qualifier.CurrentUser;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Named
@SessionScoped
public class NotificationController  implements Serializable {
        @Inject
        private NotificationService notificationService;
        @Inject
        @CurrentContact
        private Contact currentContact;
        @Inject
        @CurrentUser
        private BackendUser currentUser;
    /**
     * @return the notificationsNumber
     */
    public String consult(Notification notification) {
        return notification.getLink();
    }
    /**
     * @return the notificationsNumber
     */
    public int getContactNotificationsNumber() {
        return getContactNotifications().size();
    }
    /**
     * @return the notifications
     */
    public List<Notification> getContactNotifications() {
        return notificationService.getAllNotificationsByContact(currentContact);
    }
    /**
     * @return the notificationsNumber
     */
    public int getBackendUserNotificationsNumber() {
        return getBackendUserNotifications().size();
    }
    /**
     * @return the notifications
     */
    public List<Notification> getBackendUserNotifications() {
        return notificationService.getAllNotificationsByBackendUser(currentUser);
    }

    
}
