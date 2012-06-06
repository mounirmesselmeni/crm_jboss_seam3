/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.schedule;

import com.insat.gl5.crm_pfa.enumeration.DirectionEnum;
import com.insat.gl5.crm_pfa.enumeration.NotificationType;
import com.insat.gl5.crm_pfa.enumeration.TaskType;
import com.insat.gl5.crm_pfa.model.*;
import com.insat.gl5.crm_pfa.service.ContactService;
import com.insat.gl5.crm_pfa.service.NotificationService;
import com.insat.gl5.crm_pfa.service.TaskService;
import com.insat.gl5.crm_pfa.service.qualifier.CurrentContact;
import com.insat.gl5.crm_pfa.service.qualifier.CurrentUser;
import com.insat.gl5.crm_pfa.web.qualifier.Admin;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.seam.international.status.Messages;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
@Named
@ViewScoped
public class ScheduleController implements Serializable {

    private ScheduleModel eventModel;
    @Inject
    private TaskService taskService;
    @Inject
    private Messages messages;
    private TaskViewModel event;
    @Inject
    @CurrentUser
    private BackendUser currentBackendUser;
    @Inject
    @CurrentContact
    private Contact currentContact;
    @Inject
    private NotificationService notificationService;
    @Inject
    private ContactService contactService;

    @PostConstruct
    public void init() {
        event = new TaskViewModel();
        eventModel = new TaskScheduleModel();
        List<Task> taskList;
        if (currentBackendUser != null) {
            taskList = taskService.getContactTasks(currentBackendUser);
        } else {
            taskList = taskService.getContactTasks(currentContact);
        }
        for (Task task : taskList) {
            TaskViewModel taskViewModel = new TaskViewModel(task);
            eventModel.addEvent(taskViewModel);
        }
    }

    /**
     * Add new Task
     */
    private void createTask() {
        try {
            event.getTask().setCreator(currentBackendUser);
            this.taskService.saveTask(event.getTask());
            if (this.event.getTask().getTaskType().equals(TaskType.REUNION)) {
                notifyContact(this.event.getTask(), "Le commercial " + currentBackendUser.getFullName() + " souhaite organiser une réunion avec vous.");
            }
            eventModel.addEvent(event);
            messages.info("Tâche ajoutée.");
        } catch (Exception ex) {
            messages.error("Erreur d'ajout de votre taĉhe.");
        }
    }

    /**
     * Add new Task
     */
    private void editTask() {
        try {
            this.taskService.editTask(event.getTask());
            if (this.event.getTask().getTaskType().equals(TaskType.REUNION)) {
                if (currentBackendUser != null) {
                    notifyContact(this.event.getTask(), "Le commercial " + currentBackendUser.getFullName() + " a modifié les détails du réunion prévu.");
                }
            }
            eventModel.updateEvent(event);
            messages.info("Tâche à jour.");
        } catch (Exception ex) {
            messages.error("Erreur de mise à jour de votre taĉhe.");
        }
    }

    private void notifyContact(Task task, String content) throws Exception {
        Notification notification = new Notification();
        notification.setContent(content);
        notification.setLink("/frontoffice/agenda/viewMeeting?id" + "=" + task.getId());
        notification.setType(NotificationType.REUNION);
        List<NotificationContact> list = new LinkedList<NotificationContact>();
        Contact contact = this.contactService.findById(task.getAssignedTo().getId());
        NotificationContact notificationContact = new NotificationContact(contact, contact.getAccount().getCrmUser(), notification, DirectionEnum.FROM_BACKUSER);
        list.add(notificationContact);
        notification.setNotificationContacts(list);
        notificationService.saveNotificationContact(notificationContact);
        notificationService.saveNotification(notification);
    }

    /**
     * Add a task, create if newer edit else
     */
    @Admin
    public void addTask() {
        if (event.getId() == null) {
            createTask();
        } else {
            editTask();
        }
        event = new TaskViewModel();
    }

    /**
     * Event select event
     * @param selectEvent 
     */
    public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {
        event = (TaskViewModel) selectEvent.getScheduleEvent();
    }

    /**
     * Date select event
     * @param selectEvent 
     */
    public void onDateSelect(DateSelectEvent selectEvent) {
        Task task = new Task();
        task.setStartDate(selectEvent.getDate());
        task.setDueDate(selectEvent.getDate());
        event = new TaskViewModel(task);
    }

    /**
     * On move event
     * @param entryMoveEvent 
     */
    public void onEventMove(ScheduleEntryMoveEvent entryMoveEvent) {
        this.event = (TaskViewModel) entryMoveEvent.getScheduleEvent();
        addTask();
    }

    /**
     * On resize event
     * @param entryResizeEvent 
     */
    public void onEventResize(ScheduleEntryResizeEvent entryResizeEvent) {
        this.event = (TaskViewModel) entryResizeEvent.getScheduleEvent();
        addTask();
    }

    /**
     * @return the eventModel
     */
    public ScheduleModel getEventModel() {
        return eventModel;
    }

    /**
     * @param eventModel the eventModel to set
     */
    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public TaskViewModel getEvent() {
        return event;
    }

    public void setEvent(TaskViewModel event) {
        this.event = event;
    }
}
