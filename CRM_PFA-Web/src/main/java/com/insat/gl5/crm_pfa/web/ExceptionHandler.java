/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web;

import java.io.IOException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.jboss.seam.international.status.Messages;
import org.jboss.seam.security.AuthorizationException;
import org.jboss.seam.security.Identity;
import org.jboss.solder.exception.control.CaughtException;
import org.jboss.solder.exception.control.Handles;
import org.jboss.solder.exception.control.HandlesExceptions;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
@HandlesExceptions
public class ExceptionHandler {

    @Inject
    private Messages messages;
    @Inject
    private Identity identity;

    public void handleAuthorizationException(@Handles CaughtException<AuthorizationException> evt) {
        messages.error("Vous n'avez pas les droit nécessaire pour éffectuer cette opération");
        evt.handled();
    }

    public void handleViewExpiredException(@Handles CaughtException<ViewExpiredException> evt) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            if (identity.isLoggedIn()) {
                if (identity.hasRole("admin", "crm", "GROUP")
                        || identity.hasRole("commercial", "crm", "GROUP")) {
                    context.getExternalContext().redirect(request.getContextPath() + "/home.jsf");
                } else if (identity.hasRole("client", "crm", "GROUP")) {
                    context.getExternalContext().redirect(request.getContextPath() + "/frontoffice/index.jsf");
                }
            } else {
                context.getExternalContext().redirect(request.getContextPath() + "/login.jsf");
            }
            evt.handled();
        } catch (IOException ex) {
        }
    }
}
