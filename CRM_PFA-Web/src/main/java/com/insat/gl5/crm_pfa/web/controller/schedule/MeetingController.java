/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.schedule;

import com.insat.gl5.crm_pfa.enumeration.DirectionEnum;
import com.insat.gl5.crm_pfa.enumeration.NotificationType;
import com.insat.gl5.crm_pfa.model.BackendUser;
import com.insat.gl5.crm_pfa.model.Contact;
import com.insat.gl5.crm_pfa.model.Notification;
import com.insat.gl5.crm_pfa.model.NotificationContact;
import com.insat.gl5.crm_pfa.model.Task;
import com.insat.gl5.crm_pfa.service.ContactService;
import com.insat.gl5.crm_pfa.service.NotificationService;
import com.insat.gl5.crm_pfa.service.TaskService;
import com.insat.gl5.crm_pfa.service.qualifier.CurrentContact;
import com.insat.gl5.crm_pfa.service.qualifier.CurrentUser;
import com.insat.gl5.crm_pfa.web.controller.NotificationController;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.seam.international.status.Messages;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
@Named
@SessionScoped
public class MeetingController implements Serializable{

    private Long meetingId;
    @Inject
    private Task task;
    @Inject
    private TaskService taskService;
    @Inject
    private NotificationService notificationService;
    @Inject
    private ContactService contactService;
    @Inject
    @CurrentContact
    private Contact currentContact;
    @Inject
    @CurrentUser
    private BackendUser currentUser;
    @Inject
    private Messages messages;
    @Inject
    private NotificationController notificationController;

    public void loadTask() {
        if (meetingId == null) {
            meetingId = notificationController.getId();
        }
        if (currentUser == null) {
            this.task = taskService.getMeetingTask(meetingId, currentContact);
        } else {
            this.task = taskService.getMeetingTask(meetingId,currentUser);
        }
    }

    public String acceptMeeting() {
        return confirmMeeting(true);
    }

    public String refuseMeeting() {
        return confirmMeeting(false);
    }

    private String confirmMeeting(boolean accepted) {
        try {
            Task tempTask = taskService.getMeetingTask(meetingId, currentContact);
            tempTask.setAccepted(accepted);
            taskService.editTask(tempTask);
            String message;
            if (accepted) {
                notifyBackendUser(tempTask, "Le client " + tempTask.getAssignedTo().getFullName()
                        + " a accepté votre invitation pour le réunion.");
                message = "Réunion accepté";
            } else {
                notifyBackendUser(tempTask, "Le client " + tempTask.getAssignedTo().getFullName()
                        + " a refusé votre invitation pour le réunion.");
                message = "Réunion refusé";
            }
            this.messages.info(message);
            return "calendar";
        } catch (Exception ex) {
            this.messages.error("Erreur d'édition");
        }
        return null;
    }

    private void notifyBackendUser(Task task, String content) throws Exception {
        Notification notification = new Notification();
        notification.setContent(content);
        notification.setLink("/backoffice/agenda/viewMeeting?id" + "=" + task.getId());
        notification.setType(NotificationType.REUNION);
        List<NotificationContact> list = new LinkedList<NotificationContact>();
        Contact contact = this.contactService.findById(currentContact.getId());
        NotificationContact notificationContact = new NotificationContact(contact, task.getCreator(), notification, DirectionEnum.TO_BACKUSER);
        list.add(notificationContact);
        notification.setNotificationContacts(list);
        notificationService.saveNotificationContact(notificationContact);
        notificationService.saveNotification(notification);
    }

    /**
     * @return the meetingId
     */
    public Long getMeetingId() {
        return meetingId;
    }

    /**
     * @param meetingId the meetingId to set
     */
    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
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
