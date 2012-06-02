/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service;

import com.insat.gl5.crm_pfa.model.Contact;
import com.insat.gl5.crm_pfa.model.Ticket;
import com.insat.gl5.crm_pfa.model.TicketResponse;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
public class TicketService extends GenericService {

    /**
     * Create a ticket (by the client)
     * @param ticket
     * @throws Exception 
     */
    public void createTicket(Ticket ticket) throws Exception {
        try {
            persist(ticket);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }
    }

    /**
     * Edit a ticket (by the creator)
     * @param ticket
     * @throws Exception 
     */
    public void editTicket(Ticket ticket) throws Exception {
        try {
            edit(ticket);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }
    }

    /**
     * Add ticket response by a user (Contact or BackendUser)
     * @param ticket
     * @param ticketResponse
     * @throws Exception 
     */
    public void addResponse(Ticket ticket, TicketResponse ticketResponse) throws Exception {
        try {
            ticketResponse.setTicket(ticket);
            persist(ticketResponse);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }
    }

    /**
     * Get contact's created tickets
     * @param contact
     * @return 
     */
    public List<Ticket> getContactTicketList(Contact contact) {
        Query query = em.createQuery("select t from Ticket t where t.creator =?1 "
                + " order by t.createdOn desc");
        query.setParameter(1, contact);
        return query.getResultList();
    }

    /**
     * Get TicketResponse List for a Ticket
     * @param ticket
     * @return 
     */
    public List<TicketResponse> getLstTicketResponse(Ticket ticket) {
        Query query = em.createQuery("select t from TicketResponse t where t.ticket.id =?1 "
                + " order by t.createdOn");
        query.setParameter(1, ticket.getId());
        return query.getResultList();
    }

    /**
     * Get Ticket by id
     * @param id
     * @return 
     */
    public Ticket findTicket(Long id) {
        return em.find(Ticket.class, id);
    }

    public Long countResponse(Ticket ticket) {
        Query query = em.createQuery("select count(tr) from TicketResponse tr where tr.ticket =?1");
        query.setParameter(1, ticket);
        return (Long) query.getSingleResult();
    }

    public void editTicketResponse(TicketResponse ticketResponse) throws Exception {
        try {
            edit(ticketResponse);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }
    }
}
