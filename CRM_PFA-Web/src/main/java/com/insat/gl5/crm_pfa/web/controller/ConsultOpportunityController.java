/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller;

import com.insat.gl5.crm_pfa.enumeration.DirectionEnum;
import com.insat.gl5.crm_pfa.enumeration.NotificationType;
import com.insat.gl5.crm_pfa.model.Notification;
import com.insat.gl5.crm_pfa.model.NotificationContact;
import com.insat.gl5.crm_pfa.model.Opportunity;
import com.insat.gl5.crm_pfa.service.AccountService;
import com.insat.gl5.crm_pfa.service.NotificationService;
import com.insat.gl5.crm_pfa.service.OpportunityService;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.seam.international.status.Messages;

/**
 *
 * @author
 * Mu7ammed
 * 3li
 * --
 * mohamed.ali.affes@gmail.com
 * --
 */
@Named
@SessionScoped
public class ConsultOpportunityController implements Serializable {

    @Inject
    private OpportunityService opportunityService;
    @Inject
    private AccountService accountService;
    @Inject
    private NotificationService notificationService;
    @Inject
    private NotificationController notificationController;
    private Long id;
    private Opportunity opportunity;
    @Inject
    private Messages messages;

    public void initOpportunity() {
        opportunity = opportunityService.findById(notificationController.getId());
    }

    /**
     * @return
     * the
     * opportunity
     */
    public Opportunity getOpportunity() {
        return opportunity;
    }

    public void refuse() {
        try {
            opportunity.setAccepted(false);
            opportunityService.editOpportunity(opportunity);
            notifyBackendUser("refusé");
        } catch (Exception ex) {
            messages.error("Erreur refus");
        }
    }

    public void accept() {
        try {
            opportunity.setAccepted(true);
            opportunityService.editOpportunity(opportunity);
            
            accountService.getFidelityByContact(opportunity.getRelatedTo()).incrementScore(1);
//            opportunity.getRelatedTo().getAccount().getFidelity().incrementScore(1);
            notifyBackendUser("accepté");
        } catch (Exception ex) {
            messages.error("Erreur accept");
        }
    }

    private void notifyBackendUser(String response) throws Exception {
        Notification notification = new Notification();
        notification.setContent(opportunity.getRelatedTo().getFullName()+" a "+response+" l'opportunité ' "+opportunity.getName()+" ' !!");
        notification.setType(NotificationType.OPPORTUNITE);
        notification.setLink("/backoffice/notifications/viewOpportunity?id="+opportunity.getId());
        List<NotificationContact> list = new LinkedList<NotificationContact>();
        NotificationContact notificationContact = new NotificationContact(opportunity.getRelatedTo(), opportunity.getAssignedTo(), notification, DirectionEnum.TO_BACKUSER);
        list.add(notificationContact);
        notification.setNotificationContacts(list);
        notificationService.saveNotificationContact(notificationContact);
        notificationService.saveNotification(notification);
    }

    /**
     * @param
     * opportunity
     * the
     * opportunity
     * to
     * set
     */
    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }

    /**
     * @return
     * the
     * id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param
     * id
     * the
     * id
     * to
     * set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
