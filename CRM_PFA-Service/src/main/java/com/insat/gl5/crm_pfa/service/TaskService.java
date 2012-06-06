/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service;

import com.insat.gl5.crm_pfa.enumeration.TaskType;
import com.insat.gl5.crm_pfa.model.BackendUser;
import com.insat.gl5.crm_pfa.model.Contact;
import com.insat.gl5.crm_pfa.model.Task;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public class TaskService extends GenericService {

    private static final String SUCCESS = " with success";

    /**
     * Save new Task
     *
     * @param Task 
     */
    public void saveTask(Task Task) throws Exception {

        try {
            persist(Task);
            log.info("Create Task " + getDisplayText(Task) + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Creating Task " + getDisplayText(Task) + " : -->", ex);
            throw ex;
        }
    }

    /**
     * Delete an Task
     *
     * @param Task 
     */
    public void deleteTask(Task Task) throws Exception {
        try {
            delete(Task);
            log.info("Delete Task " + getDisplayText(Task) + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Deleting Task " + getDisplayText(Task) + " : -->", ex);
            throw ex;
        }
    }

    /**
     * Edit an Task
     *
     * @param Task 
     */
    public void editTask(Task Task) throws Exception {

        try {
            edit(Task);
            log.info("Update Task " + getDisplayText(Task) + SUCCESS);
        } catch (Exception ex) {
            log.error("Error in Updating Task " + getDisplayText(Task) + " : -->", ex);
            throw ex;
        }
    }

    /**
     * Get a list of all activities figured in database
     *
     * @return 
     */
    public List<Task> getAllTasks() {
        TypedQuery query = em.createQuery("SELECT a FROM Task a", Task.class);
        return query.getResultList();
    }

    /**
     * Get a list of Task assigned to the contact
     * @param contact
     * @return 
     */
    public List<Task> getContactTasks(Contact contact) {
        TypedQuery query = em.createQuery("SELECT a FROM Task a WHERE a.assignedTo =?1", Task.class).setParameter(1, contact);
        return query.getResultList();
    }

    /**
     * Backend user is task list
     * @param user
     * @return 
     */
    public List<Task> getContactTasks(BackendUser user) {
        TypedQuery query = em.createQuery("SELECT a FROM Task a WHERE a.creator =?1", Task.class).setParameter(1, user);
        return query.getResultList();
    }

    public Task getMeetingTask(Long taskId, Contact contact) {
        TypedQuery query = em.createQuery("SELECT a FROM Task a "
                + "WHERE a.assignedTo =?1 "
                + "AND a.taskType =?2", Task.class).setParameter(1, contact).setParameter(2, TaskType.REUNION);
        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return (Task)query.getResultList().get(0);
        }
    }

    private String getDisplayText(Task Task) {
        return Task.getTaskType().getDisplayName() + " : " + Task.getSubject();
    }
}
