/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
@Entity
public class TicketResponse extends BaseEntity{
    @Lob
    private String content;
    @ManyToOne
    private BackendUser backendUser;
    @ManyToOne
    private Ticket ticket;

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
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
     * @return the backendUser
     */
    public BackendUser getBackendUser() {
        return backendUser;
    }

    /**
     * @param backendUser the backendUser to set
     */
    public void setBackendUser(BackendUser backendUser) {
        this.backendUser = backendUser;
    }
}
