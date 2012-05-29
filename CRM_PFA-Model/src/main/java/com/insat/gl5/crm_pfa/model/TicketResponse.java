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
    private Contact contact;
    @ManyToOne
    private Ticket ticket;
}
