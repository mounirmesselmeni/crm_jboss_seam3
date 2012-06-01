/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.converter.account;

import com.insat.gl5.crm_pfa.model.Contact;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@RequestScoped
@FacesConverter(forClass = Contact.class)
public class ContactConverter implements Converter {

    @Inject
    private List<Contact> lstContacts;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null) {
            return null;
        }
        for (Contact contact : lstContacts) {
            if (contact.getFullName().equals(string)) {
                return contact;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object == null) {
            return null;
        }
        return ((Contact) object).getFullName();
    }
}
