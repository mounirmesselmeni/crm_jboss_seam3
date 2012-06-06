/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.schedule;

import com.insat.gl5.crm_pfa.model.Contact;
import com.insat.gl5.crm_pfa.model.Task;
import com.insat.gl5.crm_pfa.service.TaskService;
import com.insat.gl5.crm_pfa.service.qualifier.CurrentContact;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.seam.international.status.Messages;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
@Named
@RequestScoped
public class MeetingController implements Serializable {

    private Long meetingId;
    @Inject
    private Task task;
    @Inject
    private TaskService taskService;
    @Inject
    @CurrentContact
    private Contact contact;
    @Inject
    private Messages messages;

    public void loadTask() {
        if (contact != null) {
            this.task = taskService.getMeetingTask(meetingId, contact);
        } else {
            messages.error("Vous n'avez pas les droit nécessaire pour éffectuer cette opération");
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
            Task tempTask = taskService.getMeetingTask(meetingId, contact);
            tempTask.setAccepted(accepted);
            taskService.editTask(tempTask);
            String message;
            if (accepted) {
                message = "Réunion accepté";
            } else {
                message = "Réunion refusé";
            }
            this.messages.info(message);
            return "calendar";
        } catch (Exception ex) {
            this.messages.error("Erreur d'édition");
        }
        return null;
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
