/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service.producer;

import com.insat.gl5.crm_pfa.model.Contact;
import com.insat.gl5.crm_pfa.service.qualifier.DataRepository;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.jboss.solder.logging.Logger;
import org.picketlink.idm.api.Group;
import org.picketlink.idm.api.IdentitySession;
import org.picketlink.idm.api.RoleType;
import org.picketlink.idm.common.exception.FeatureNotSupportedException;
import org.picketlink.idm.common.exception.IdentityException;

/**
 *
 * @author Mounir Messelmeni, contact: messelmeni.mounir@gmail.com
 */
public class GroupRoleProducer {

    @Inject
    private IdentitySession identitySession;
    @Inject
    private Logger log;
    @Inject
    @DataRepository
    private EntityManager em;

    /**
     * Role types list
     * @return 
     */
    @Produces
    @Named("lstRoleTypes")
    public List<RoleType> getLstRoleTypes() {
        try {
            List<RoleType> lstRoleTypes = new LinkedList<RoleType>();
            for (RoleType roleType : this.identitySession.getRoleManager().findRoleTypes()) {
                lstRoleTypes.add(roleType);
            }
            return lstRoleTypes;
        } catch (IdentityException ex) {
            log.error(ex.getMessage());
            return null;
        } catch (FeatureNotSupportedException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

    /**
     * Group list
     * @return 
     */
    @Produces
    @Named("lstGroups")
    public List<Group> getLstGroups() {
        try {
            List<Group> lstGroups = new LinkedList<Group>();
            for (Group group : this.identitySession.getPersistenceManager().findGroup("GROUP")) {
                lstGroups.add(group);
            }
            return lstGroups;
        } catch (IdentityException ex) {
            log.error(ex.getMessage());
            return null;
        }
    }

}
