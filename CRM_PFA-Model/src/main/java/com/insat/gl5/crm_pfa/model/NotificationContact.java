/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import com.insat.gl5.crm_pfa.enumeration.DirectionEnum;
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
    @ManyToOne
    private BackendUser backendUser;
    private  DirectionEnum direction;
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

    /**
     * @return the direction
     */
    public DirectionEnum getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }
}
