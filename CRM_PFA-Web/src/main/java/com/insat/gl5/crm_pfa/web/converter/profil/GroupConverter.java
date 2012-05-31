/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.converter.profil;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.jboss.solder.logging.Logger;
import org.picketlink.idm.api.Group;
import org.picketlink.idm.impl.api.model.SimpleGroup;

/**
 *
 * @author Mounir Messelmeni, contact: messelmeni.mounir@gmail.com
 */
@FacesConverter(forClass = Group.class)
@RequestScoped
public class GroupConverter implements Converter {

    @Inject
    private Logger log;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return new SimpleGroup(value, "GROUP");
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            Group group = (Group) value;
            if (group == null) {
                return null;
            }
            return group.getName();
        } catch (ClassCastException ex) {
            log.error("GroupConverter: " + ex.getMessage());
            return null;
        }
    }
}
