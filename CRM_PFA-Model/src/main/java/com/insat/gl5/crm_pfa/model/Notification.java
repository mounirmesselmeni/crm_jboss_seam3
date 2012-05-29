/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import com.insat.gl5.crm_pfa.enumeration.NotificationType;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
@Entity
public class Notification extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private NotificationType type;
    @OneToMany(mappedBy = "notification")
    private List<NotificationContact> notificationContacts = new LinkedList<NotificationContact>();
    private String link;
    private String content;
    private boolean readed = false;

    /**
     * @return the type
     */
    public NotificationType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(NotificationType type) {
        this.type = type;
    }

    /**
     * @return the notificationContacts
     */
    public List<NotificationContact> getNotificationContacts() {
        return notificationContacts;
    }

    /**
     * @param notificationContacts the notificationContacts to set
     */
    public void setNotificationContacts(List<NotificationContact> notificationContacts) {
        this.notificationContacts = notificationContacts;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
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
     * @return the readed
     */
    public boolean isReaded() {
        return readed;
    }

    /**
     * @param readed the readed to set
     */
    public void setReaded(boolean readed) {
        this.readed = readed;
    }
}
