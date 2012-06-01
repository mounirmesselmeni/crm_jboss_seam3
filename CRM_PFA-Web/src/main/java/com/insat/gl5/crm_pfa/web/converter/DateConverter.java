/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */

@FacesConverter(value = "dateConverter")
public class DateConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            return fromStringToDate(value);
        } catch (ParseException ex) {
            Logger.getLogger(DateConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Date date = (Date) value;
        return fromDateToString(date);
    }

    public String fromDateToString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy à HH:mm:ss");
        return formatDateJour.format(date);
    }

    public Date fromStringToDate(String date) throws ParseException {
        SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy à HH:mm:ss");
        return formatDateJour.parse(date);
    }
}
