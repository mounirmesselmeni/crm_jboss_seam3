/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.qualifier;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jboss.seam.faces.event.PhaseIdType;
import org.jboss.seam.security.annotations.SecurityBindingType;
import org.jboss.seam.faces.security.RestrictAtPhase;

@SecurityBindingType
@RestrictAtPhase(PhaseIdType.RESTORE_VIEW)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD,ElementType.FIELD})
public @interface Admin {
}
