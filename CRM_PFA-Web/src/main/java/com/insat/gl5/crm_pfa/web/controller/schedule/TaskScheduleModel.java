/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
public class TaskScheduleModel implements ScheduleModel {

    private List<ScheduleEvent> events;

    public TaskScheduleModel() {
        events = new ArrayList<ScheduleEvent>();
    }

    public TaskScheduleModel(List<ScheduleEvent> events) {
        this.events = events;
    }

    @Override
    public void addEvent(ScheduleEvent event) {
        event.setId(UUID.randomUUID().toString());

        events.add(event);
    }

    @Override
    public boolean deleteEvent(ScheduleEvent event) {
        return events.remove(event);
    }

    @Override
    public List<ScheduleEvent> getEvents() {
        return events;
    }

    @Override
    public ScheduleEvent getEvent(String id) {
        for (ScheduleEvent event : events) {
            if (event.getId().equals(id)) {
                return event;
            }
        }

        return null;
    }

    @Override
    public void updateEvent(ScheduleEvent event) {
        int index = -1;

        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId().equals(event.getId())) {
                index = i;

                break;
            }
        }

        if (index >= 0) {
            events.set(index, event);
        }
    }

    @Override
    public int getEventCount() {
        return events.size();
    }

    @Override
    public void clear() {
        events = new ArrayList<ScheduleEvent>();
    }
}
