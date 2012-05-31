/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
import com.insat.gl5.crm_pfa.web.qualifier.Admin;
import com.insat.gl5.crm_pfa.web.qualifier.Commercial;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.annotations.Secures;

public class Restrictions {

    public @Secures
    @Admin
    boolean isAdmin(Identity identity) {
        return identity.hasRole("admin", "crm", "GROUP");
    }

    public @Secures
    @Commercial
    boolean isCommercial(Identity identity) {
        return identity.hasRole("commercial", "crm", "GROUP");
    }
}