/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.ticket;

import com.insat.gl5.crm_pfa.model.BackendUser;
import com.insat.gl5.crm_pfa.model.Contact;
import com.insat.gl5.crm_pfa.model.Ticket;
import com.insat.gl5.crm_pfa.model.TicketResponse;
import com.insat.gl5.crm_pfa.service.TicketService;
import com.insat.gl5.crm_pfa.service.qualifier.CurrentContact;
import com.insat.gl5.crm_pfa.service.qualifier.CurrentUser;
import com.insat.gl5.crm_pfa.web.controller.ConversationController;
import java.util.List;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
    private Identity identity;

    /**
     * Create ticket by client (Contact)
     */
    public String createTicket() {
        if (currentContact == null) {
            messages.error("Utilisateur inconnu !");
            return null;
        }
        try {
            getTicket().setCreator(currentContact);
            ticketService.createTicket(ticket);
            ticket = new Ticket();
            return "list";
        } catch (Exception ex) {
            messages.error("Erreur lors de la création du ticket");
        }
        return null;
    }

    public String editTicket() {
        if (currentContact == null) {
            messages.error("Utilisateur inconnu !");
            return null;
        }
        if (!currentContact.equals(ticket.getCreator())) {
            messages.error("Vous n'avez pas le droit de modifier ce contenu !");
            return null;
        }
        try {
            ticketService.editTicket(ticket);
            ticket = new Ticket();
            return "list";
        } catch (Exception ex) {
            messages.error("Erreur lors de la création du ticket");
        }
        return null;
    }

    /**
     * response to the current ticket
     * @return 
     */
    public String addResponse() {
        if (identity.hasRole("admin", "crm", "GROUP")
                || identity.hasRole("commercial", "crm", "GROUP")) {
            if (currentBackendUser == null) {
                messages.error("Utilisateur inconnu !");
                return null;
            }
            try {
                getTicketResponse().setBackendUser(currentBackendUser);
                ticketService.addResponse(getTicket(), getTicketResponse());
                return "list";
            } catch (Exception ex) {
                messages.error("Erreur lors de l'insertion de la réponse.");
                return null;
            }
        }
        return null;
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
}
