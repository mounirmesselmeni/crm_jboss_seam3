/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.ticket;

import com.insat.gl5.crm_pfa.enumeration.DirectionEnum;
import com.insat.gl5.crm_pfa.enumeration.NotificationType;
import com.insat.gl5.crm_pfa.model.*;
import com.insat.gl5.crm_pfa.service.ContactService;
import com.insat.gl5.crm_pfa.service.NotificationService;
import com.insat.gl5.crm_pfa.service.TicketService;
import com.insat.gl5.crm_pfa.service.qualifier.CurrentContact;
import com.insat.gl5.crm_pfa.service.qualifier.CurrentUser;
import com.insat.gl5.crm_pfa.web.controller.ConversationController;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.LazyInitializationException;
import org.jboss.seam.international.status.Messages;
import org.jboss.seam.security.Identity;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
@Named
@ConversationScoped
public class TicketController extends ConversationController {

    @Inject
    private Ticket ticket;
    @Inject
    private TicketResponse ticketResponse;
    @Inject
    private Messages messages;
    @Inject
    @CurrentContact
    private Contact currentContact;
    @Inject
    @CurrentUser
    private BackendUser currentBackendUser;
    @Inject
    private TicketService ticketService;
    @Inject
    private NotificationService notificationService;
    @Inject
    private ContactService contactService;
    @Inject
    private Identity identity;
    private Long ticketId;

    /**
     * Load Ticket by Id
     */
    public void loadTicket() {
        this.ticket = this.ticketService.findTicket(this.ticketId);
        if (!isAllowedUser()) {
            //if user not allowed nothing gonna to be viewed by resting the ticket
            ticket = new Ticket();
            messages.error("Attention ! Vous n'êtes pas autorisé à accéder à ce contenu.");
        }
    }

    /**
     * Load new TicketResponse instance
     */
    public void initTicketResponse() {
        this.ticketResponse = new TicketResponse();
    }

    /**
     * Verify if the current user is allowed to view the Ticket
     * @return 
     */
    public boolean isAllowedUser() {
        if (currentBackendUser != null) {
            return true;
        }
        if (currentContact != null && this.ticket.getCreator().equals(currentContact)) {
            return true;
        }
        return false;
    }

    /**
     * Verify if the current user is the creator of the current Ticket
     * @return 
     */
    public boolean canEditTicket() {
        if (currentContact == null) {
            return false;
        }
        if (currentContact.equals(ticket.getCreator())) {
            return true;
        }
        return false;
    }

    /**
     * Verify if the current user is the creator of the current TicketResponse
     * @return 
     */
    public boolean canEditTicketResponse(TicketResponse currentTicketResponse) {
        if (noUserConnected()) {
            return false;
        }
        if (currentContact != null && currentTicketResponse != null
                && currentContact.equals(currentTicketResponse.getCrmUser())) {
            return true;
        }
        if (currentBackendUser != null && currentTicketResponse != null
                && currentBackendUser.equals(currentTicketResponse.getCrmUser())) {
            return true;
        }
        return false;
    }

    /**
     * Create ticket by client (Contact)
     */
    public String createTicket() {
        if (this.currentContact == null) {
            this.messages.error("Utilisateur inconnu !");
            return null;
        }
        try {
            this.ticket.setCreator(this.currentContact);
            this.ticketService.createTicket(this.ticket);
            notifyBackendUser(this.ticket, "Le client " + currentContact.getFullName() + " a postulé un nouveau ticket");
            this.ticket = new Ticket();
            this.messages.info("Ticket crée avec succés.");
            return "list";
        } catch (Exception ex) {
            this.messages.error("Erreur lors de la création du ticket");
        }
        return null;
    }

    private void notifyBackendUser(Ticket tick, String content) throws Exception {
        Notification notification = new Notification();
        notification.setContent(content);
        notification.setLink("/backoffice/ticket/view?id" + "=" + tick.getId());
        notification.setType(NotificationType.TICKET);
        List<NotificationContact> list = new LinkedList<NotificationContact>();
        Contact contact = this.contactService.findById(currentContact.getId());
        NotificationContact notificationContact = new NotificationContact(contact, contact.getAccount().getCrmUser(), notification, DirectionEnum.TO_BACKUSER);
        list.add(notificationContact);
        notification.setNotificationContacts(list);
        notificationService.saveNotificationContact(notificationContact);
        notificationService.saveNotification(notification);
    }

    private void notifyContact(Ticket tick, String content) throws Exception {
        Notification notification = new Notification();
        notification.setContent(content);
        notification.setLink("/frontoffice/ticket/view?id" + "=" + tick.getId());
        notification.setType(NotificationType.TICKET);
        List<NotificationContact> list = new LinkedList<NotificationContact>();
        Contact contact = this.contactService.findById(tick.getCreator().getId());
        NotificationContact notificationContact = new NotificationContact(contact, contact.getAccount().getCrmUser(), notification, DirectionEnum.FROM_BACKUSER);
        list.add(notificationContact);
        notification.setNotificationContacts(list);
        notificationService.saveNotificationContact(notificationContact);
        notificationService.saveNotification(notification);
    }

    public String editTicket() {
        if (this.currentContact == null) {
            this.messages.error("Utilisateur inconnu !");
            return null;
        }
        if (!this.currentContact.equals(this.ticket.getCreator())) {
            this.messages.error("Vous n'avez pas le droit de modifier ce contenu !");
            return null;
        }
        try {
            this.ticketService.editTicket(this.ticket);
            notifyBackendUser(this.ticket, "Le client " + currentContact.getFullName() + " a mis à jour son ticket");
            this.messages.info("Ticket à jour.");
            return "view";
        } catch (Exception ex) {
            this.messages.error("Erreur lors de la création du ticket");
        }
        return null;
    }

    /**
     * Update the resolved boolean to the Data base
     */
    public void editResolved() {
        if (this.currentContact != null && this.currentContact.equals(this.ticket.getCreator())) {
            try {
                this.ticket.setResolved(!this.ticket.isResolved());
                this.ticketService.editTicket(this.ticket);
                if (this.ticket.isResolved()) {
                    notifyBackendUser(this.ticket, "Le client " + currentContact.getFullName() + " a marqué le ticket résolu.");
                    this.messages.info("Ticket résolu");
                } else {
                    notifyBackendUser(this.ticket, "Le client " + currentContact.getFullName() + " a marqué le ticket non résolu.");
                    this.messages.info("Ticket non résolu");
                }
            } catch (Exception ex) {
                this.messages.error("Erreur lors de la mise à jour du ticket");
            }
        }
    }

    /**
     * response to the current ticket
     * @return 
     */
    public void addResponse() {
        if (this.identity.hasRole("admin", "crm", "GROUP")
                || this.identity.hasRole("commercial", "crm", "GROUP")) {
            if (currentBackendUser == null) {
                this.messages.error("Utilisateur inconnu !");
                return;
            }
            try {
                this.ticketResponse.setCrmUser(currentBackendUser);
                Ticket ticketE = ticketService.findTicket(ticketId);
                this.ticketService.addResponse(ticketE, this.ticketResponse);
                notifyContact(ticketE, "Le commercial " + currentBackendUser.getFullName() + " a ajouté une réponse a votre ticket.");
                this.ticketResponse = new TicketResponse();
                this.messages.info("Réponse ajouté.");
                return;
            } catch (Exception ex) {
                this.messages.error("Erreur lors de l'insertion de la réponse.");
                return;
            }
        }
        if (this.identity.hasRole("client", "crm", "GROUP")) {
            if (this.currentContact == null) {
                this.messages.error("Utilisateur inconnu !");
                return;
            }
            try {
                this.ticketResponse.setCrmUser(this.currentContact);
                Ticket ticketE = ticketService.findTicket(ticketId);
                this.ticketService.addResponse(ticketE, this.ticketResponse);
                notifyBackendUser(ticketE, "Le client " + currentContact.getFullName() + " a ajouté une réponse a son ticket.");
                this.ticketResponse = new TicketResponse();
                this.messages.info("Réponse ajouté.");
            } catch (Exception ex) {
                this.messages.error("Erreur lors de l'insertion de la réponse.");
            }
        }
    }

    /**
     * Edit the current TicketResponse
     * @return 
     */
    public String editResponse() {
        if (noUserConnected()) {
            return null;
        }
        try {
            this.ticketService.editTicketResponse(this.ticketResponse);
            Ticket ticketE = ticketService.findTicket(ticketId);
            if (currentContact != null) {
                notifyBackendUser(ticketE, "Le client " + currentContact.getFullName() + " a ajouté une réponse a son ticket.");
            } else if (currentBackendUser != null) {
                notifyContact(ticketE, "Le commercial " + currentBackendUser.getFullName() + " a ajouté une réponse a votre ticket.");
            }
            this.messages.info("Réponse à jour");
            return "view";
        } catch (Exception ex) {
            this.messages.error("Erreur lors de la modification de votre réponse.");
            return null;
        }
    }

    private boolean noUserConnected() {
        if (this.currentContact == null && this.currentBackendUser == null) {
            this.messages.error("Utilisateur inconnu !");
            return true;
        }
        return false;
    }

    public Long getReponseNumber(Ticket ticketE) {
        return this.ticketService.countResponse(ticketE);
    }

    /**
     * Get the current contact is created Tickets list
     * @return 
     */
    public List<Ticket> getContactTickets() {
        if (this.currentContact == null) {
            return null;
        }
        return ticketService.getContactTicketList(currentContact);
    }

    /**
     * List of TicketResponse for the current Ticket
     * @return 
     */
    public List<TicketResponse> getTicketResponseList() {
        return ticketService.getLstTicketResponse(ticket);
    }

    /**
     * @return the ticket
     */
    public Ticket getTicket() {
        return ticket;
    }

    /**
     * @param ticket the ticket to set
     */
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    /**
     * @return the ticketResponse
     */
    public TicketResponse getTicketResponse() {
        return ticketResponse;
    }

    /**
     * @param ticketResponse the ticketResponse to set
     */
    public void setTicketResponse(TicketResponse ticketResponse) {
        this.ticketResponse = ticketResponse;
    }

    /**
     * @return the ticketId
     */
    public Long getTicketId() {
        return ticketId;
    }

    /**
     * @param ticketId the ticketId to set
     */
    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }
}
