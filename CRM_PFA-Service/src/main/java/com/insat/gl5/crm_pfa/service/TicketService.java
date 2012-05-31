/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service;

import com.insat.gl5.crm_pfa.model.Ticket;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
public class TicketService extends GenericService {

    public void createTicket(Ticket ticket) throws Exception {
        try {
            persist(ticket);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    public void editTicket(Ticket ticket) throws Exception {
        try {
            edit(ticket);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
    
//    public
}
