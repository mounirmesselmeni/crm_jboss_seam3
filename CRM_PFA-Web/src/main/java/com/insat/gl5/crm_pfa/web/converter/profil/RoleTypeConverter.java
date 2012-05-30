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
import org.picketlink.idm.api.IdentitySession;
import org.picketlink.idm.api.RoleType;
import org.picketlink.idm.common.exception.FeatureNotSupportedException;
import org.picketlink.idm.common.exception.IdentityException;

/**
 *
 * @author Mounir Messelmeni, contact: messelmeni.mounir@gmail.com
 */
@FacesConverter(forClass = RoleType.class)
@RequestScoped
public class RoleTypeConverter implements Converter {

    @Inject
    private IdentitySession identitySession;
    @Inject
    private Logger log;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            for (RoleType roleType : this.identitySession.getRoleManager().findRoleTypes()) {
                if (roleType.getName().equals(value)) {
                    return roleType;
                }
            }
        } catch (IdentityException ex) {
            log.error("RoleTypeConverter : " + ex.getMessage());
            return null;
        } catch (FeatureNotSupportedException ex) {
            log.error("RoleTypeConverter : " + ex.getMessage());
            return null;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            RoleType roleType = (RoleType) value;
            if (roleType == null) {
                return null;
            }
            return roleType.getName();
        } catch (ClassCastException ex) {
            log.error("RoleTypeConverter : " + ex.getMessage());
            return null;
        }
    }
}
