/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.producer;

import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
public class TimeProducer {

    @Produces
    @Named("hourOfTheDay")
    @ApplicationScoped
    public List<String> timeOfTheDayList() {
        List<String> lstOfTime = new LinkedList<String>();
        String hourStr, minuteStr;
        for (int hour = 0; hour < 24; hour++) {
            hourStr = hourToString(hour);
            for (int minute = 0; minute < 60; minute+=15) {
                minuteStr = minuteToString(minute);
                lstOfTime.add(hourStr + ":" + minuteStr);
            }
        }
        return lstOfTime;
    }

    private String hourToString(int hour) {
        String hourStr;
        if (hour < 10) {
            hourStr = "0" + String.valueOf(hour);
        } else {
            hourStr = String.valueOf(hour);
        }
        return hourStr;
    }

    private String minuteToString(int minute) {
        String minuteStr;
        if (minute < 10) {
            minuteStr = "0" + String.valueOf(minute);
        } else {
            minuteStr = String.valueOf(minute);
        }
        return minuteStr;
    }
}
