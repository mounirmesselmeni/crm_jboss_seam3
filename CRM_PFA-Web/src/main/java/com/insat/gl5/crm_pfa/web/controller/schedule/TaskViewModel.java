/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.schedule;

import com.insat.gl5.crm_pfa.model.Task;
import java.util.Date;
import org.primefaces.model.ScheduleEvent;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
public class TaskViewModel implements ScheduleEvent {

    private String id;
    private String title;
    private Date startDate;
    private Date endDate;
    private boolean allDay;
    private String styleClass;
    private Object data;
    private boolean editable;
    private Task task;

    public TaskViewModel() {
        this.task = new Task();
    }

    public TaskViewModel(Task task) {
        this.task = task;
    }
    
    

    /**
     * @return the title
     */
    @Override
    public String getTitle() {
        return task.getSubject();
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the startDate
     */
    @Override
    public Date getStartDate() {
        return task.getStartDate();
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    @Override
    public Date getEndDate() {
        return task.getDueDate();
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the allDay
     */
    @Override
    public boolean isAllDay() {
        return false;
    }

    /**
     * @param allDay the allDay to set
     */
    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    /**
     * @return the styleClass
     */
    @Override
    public String getStyleClass() {
        return styleClass;
    }

    /**
     * @param styleClass the styleClass to set
     */
    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    /**
     * @return the data
     */
    @Override
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @return the editable
     */
    @Override
    public boolean isEditable() {
        return true;
    }

    /**
     * @param editable the editable to set
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     * @return the id
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId(String id) {
        this.id = id;
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
