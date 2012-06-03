/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.schedule;

import com.insat.gl5.crm_pfa.model.BackendUser;
import com.insat.gl5.crm_pfa.model.Task;
import com.insat.gl5.crm_pfa.service.TaskService;
import com.insat.gl5.crm_pfa.service.qualifier.CurrentUser;
import java.io.Serializable;
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

    @Inject
    private Task task;
    private ScheduleModel eventModel;
    @Inject
    private TaskService taskService;
    @Inject
    private Messages messages;
    private TaskViewModel event;
    @Inject
    @CurrentUser
    private BackendUser currentBackendUser;

    @PostConstruct
    public void init() {
        event = new TaskViewModel();
        eventModel = new TaskScheduleModel();
        for (Task task : taskService.getAllTasks()) {
            TaskViewModel taskViewModel = new TaskViewModel(task);
            eventModel.addEvent(taskViewModel);
        }
    }

    /**
     * Init Task
     */
    public void resetTask() {
        task = new Task();
    }

    /**
     * Add new Task
     */
    public void createTask() {
        try {
            event.getTask().setCreator(currentBackendUser);
            this.taskService.saveTask(event.getTask());
            eventModel.addEvent(event);
            messages.info("Tâche ajoutée.");
        } catch (Exception ex) {
            messages.error("Erreur d'ajout de votre taĉhe.");
        }
    }

    /**
     * Add new Task
     */
    public void editTask() {
        try {
            this.taskService.editTask(event.getTask());
            eventModel.updateEvent(event);
            messages.info("Tâche à jour.");
        } catch (Exception ex) {
            messages.error("Erreur de mise à jour de votre taĉhe.");
        }
    }

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
        task = new Task();
        task.setStartDate(event.getStartDate());
        task.setDueDate(event.getEndDate());
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
