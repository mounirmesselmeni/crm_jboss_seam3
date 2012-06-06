/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web;

import java.io.IOException;
import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.jboss.seam.international.status.Messages;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.events.LoginFailedEvent;
import org.jboss.solder.logging.Logger;

/**
 *
 * @author Mounir Messelmeni, contact: messelmeni.mounir@gmail.com
 */
public class LoginObserver {

    @Inject
    private Logger log;
    @Inject
    private Messages messages;
    @Inject
    private Identity identity;

    /**
     * Handling login success event
     * @param event 
     */
    public void loginSuccess(@Observes org.jboss.seam.security.events.LoggedInEvent event) {
        try {
            log.info("Login successfully for user : " + event.getUser().getId());
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            //Redirect to the home page
            if (identity.hasRole("admin", "crm", "GROUP")
                    || identity.hasRole("commercial", "crm", "GROUP")) {
                context.getExternalContext().redirect(request.getContextPath() + "/backoffice/home.jsf");
            }else if(identity.hasRole("client", "crm", "GROUP")){
                context.getExternalContext().redirect(request.getContextPath() + "/frontoffice/home.jsf");
            }
            //Sending welcome message
            messages.info("Bienvenue "+event.getUser().getId());
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
    }

    /**
     * Handling Login failed event
     * @param event
     * @throws IOException 
     */
    public void loginFailed(@Observes LoginFailedEvent event) throws IOException {
        if (event.getLoginException() != null) {
            log.info("Login failed " + event.getLoginException().getMessage());
        } else {
            log.info("Login failed");
        }
        messages.error("e-mail ou mot de passe invalide");
    }
}
