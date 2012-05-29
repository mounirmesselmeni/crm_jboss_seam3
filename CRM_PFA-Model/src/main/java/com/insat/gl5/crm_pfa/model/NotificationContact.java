/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
@Entity
public class NotificationContact extends BaseEntity {

    @ManyToOne
    private Contact contact;
    @ManyToOne
    private Notification notification;

    /**
     * @return the contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * @return the notification
     */
    public Notification getNotification() {
        return notification;
    }

    /**
     * @param notification the notification to set
     */
    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
