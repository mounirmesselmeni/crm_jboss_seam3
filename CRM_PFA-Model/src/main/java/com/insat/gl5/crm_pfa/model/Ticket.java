/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import com.insat.gl5.crm_pfa.enumeration.TicketType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
@Entity
public class Ticket extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private TicketType type;
    private String subject;
    @Lob
    private String content;
    @ManyToOne
    private Contact creator;
    private boolean resolved = false;

    /**
     * @return the type
     */
    public TicketType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(TicketType type) {
        this.type = type;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

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
     * @return the resolved
     */
    public boolean isResolved() {
        return resolved;
    }

    /**
     * @param resolved the resolved to set
     */
    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    /**
     * @return the creator
     */
    public Contact getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(Contact creator) {
        this.creator = creator;
    }
    
}
