/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.producer;

import com.insat.gl5.crm_pfa.model.BackendUser;
import com.insat.gl5.crm_pfa.model.Contact;
import com.insat.gl5.crm_pfa.service.qualifier.CurrentContact;
import com.insat.gl5.crm_pfa.service.qualifier.CurrentUser;
import com.insat.gl5.crm_pfa.service.security.UserProfileService;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.seam.security.Identity;
import org.jboss.solder.logging.Logger;
import org.picketlink.idm.api.IdentitySession;
import org.picketlink.idm.api.Role;
import org.picketlink.idm.api.RoleType;
import org.picketlink.idm.common.exception.FeatureNotSupportedException;
import org.picketlink.idm.common.exception.IdentityException;
import org.picketlink.idm.impl.api.model.SimpleUser;

/**
 *
 * @author Mounir Messelmeni, contact: messelmeni.mounir@gmail.com
 */
public class UserProducer {

    @Inject
    private Identity identity;
    @Inject
    private IdentitySession identitySession;
    @Inject
    private UserProfileService profileService;
    @Inject
    private Logger logger;

    /**
     * Current Contact
     * @return 
     */
    @Produces
    @CurrentContact
    @Named("currentContact")
    public Contact getCurrentContact() {
        if (identity.getUser() == null) {
            return null;
        }
        return profileService.loadProfilUser(identity.getUser().getId());
    }

    /**
     * Current User
     * @return 
     */
    @Produces
    @CurrentUser
    @Named("currentUser")
    public BackendUser getCurrentUser() {
        if (identity.getUser() == null) {
            return null;
        }
        return profileService.loadBackendUser(identity.getUser().getId());
    }

    /**
     * Current user roles
     * @return 
     */
    @Produces
    @Named("currentUserRoles")
    public List<Role> getCurrentUserRoles() {
        if (identity.getUser() == null) {
            return null;
        }
        try {
            Collection<RoleType> roleTypesCollection = identitySession.getRoleManager().findUserRoleTypes(new SimpleUser(identity.getUser().getId()));
            List<Role> roles = new LinkedList<Role>();
            for (RoleType roleTypeS : roleTypesCollection) {
                roles.addAll(identitySession.getRoleManager().findRoles(identity.getUser().getId(), roleTypeS.getName()));
            }
            return roles;
        } catch (FeatureNotSupportedException ex) {
            logger.error("CurrentUserRoles Producer : " + ex.getMessage());
        } catch (IdentityException ex) {
            logger.error("CurrentUserRoles Producer : " + ex.getMessage());
        }
        return null;
    }
}
