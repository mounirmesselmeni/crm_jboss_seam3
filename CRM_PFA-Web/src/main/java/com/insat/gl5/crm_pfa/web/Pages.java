/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web;

import com.insat.gl5.crm_pfa.web.qualifier.Admin;
import org.jboss.seam.faces.security.AccessDeniedView;
import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;
import org.jboss.seam.security.annotations.LoggedIn;

/**
 *
 * @author Mounir Messelmeni, contact: messelmeni.mounir@gmail.com
 */
@ViewConfig
public interface Pages {

    static enum Configuration {

        @ViewPattern("/backoffice/*")
        @Admin
        BACKOFFICE,
        
        @ViewPattern("/frontoffice/*")
        @LoggedIn
        FRONTOFFICE,
        
        @ViewPattern("/*")
        @AccessDeniedView("/denied.xhtml")
        @LoginView("/login.xhtml")
        ALL;
    }
}
