/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
@Entity
public class MeetingConfirmation extends BaseEntity {

    private boolean confirmed = false;
    @ManyToOne
    private Contact contactToConfirm;
    @Lob
    private String content;
    @OneToOne
    private Task task;

    /**
     * @return the confirmed
     */
    public boolean isConfirmed() {
        return confirmed;
    }

    /**
     * @param confirmed the confirmed to set
     */
    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    /**
     * @return the contactToConfirm
     */
    public Contact getContactToConfirm() {
        return contactToConfirm;
    }

    /**
     * @param contactToConfirm the contactToConfirm to set
     */
    public void setContactToConfirm(Contact contactToConfirm) {
        this.contactToConfirm = contactToConfirm;
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
     * @return the task
     */
    public Task getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(Task task) {
        this.task = task;
    }
}
