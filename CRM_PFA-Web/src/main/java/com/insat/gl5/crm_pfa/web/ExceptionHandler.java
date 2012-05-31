/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web;

import javax.inject.Inject;
import org.jboss.seam.international.status.Messages;
import org.jboss.seam.security.AuthorizationException;
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

    public void handleAuthorizationException(@Handles CaughtException<AuthorizationException> evt) {
        messages.error("You do not have the necessary permissions to perform that operation");
        evt.handled();
    }
}
