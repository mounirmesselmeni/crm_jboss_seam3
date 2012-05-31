/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.security;

import com.insat.gl5.crm_pfa.model.BackendUser;
import com.insat.gl5.crm_pfa.model.Contact;
import com.insat.gl5.crm_pfa.web.qualifier.Admin;
import com.insat.gl5.crm_pfa.service.security.UserProfileService;
import com.insat.gl5.crm_pfa.web.controller.ConversationController;
import java.util.Collection;
import java.util.LinkedList;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.seam.international.status.Messages;
import org.picketlink.idm.api.Group;
import org.picketlink.idm.api.Role;
import org.picketlink.idm.api.RoleType;
import org.picketlink.idm.common.exception.FeatureNotSupportedException;
import org.picketlink.idm.common.exception.IdentityException;
import org.picketlink.idm.impl.api.model.SimpleRole;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
@Named
@ConversationScoped
public class UserManagement extends ConversationController {

    @Inject
    private UserProfileService profileService;
    @Inject
    private BackendUser user;
    private String password;
    private String confirmPassword;
    private Collection<Role> roles = new LinkedList<Role>();
    private RoleType roleType;
    private Group roleGroup;
    @Inject
    private Messages messages;

    public String createUser() {
        if (verifyConfirmPassword()) {
            return null;
        }
        try {
            this.profileService.saveNewUser(this.user, this.password, this.roles);
            this.user = new BackendUser();
            endConversation();
            return "/home";
        } catch (FeatureNotSupportedException ex) {
            this.messages.error("FeatureNotSupportedException");
        } catch (IdentityException ex) {
            this.messages.error("IdentityException");
        }
        return null;

    }
    public String createClientAccount(Contact contact) {
        if (verifyConfirmPassword()) {
            return null;
        }
        try {
            this.profileService.saveNewUser(contact, this.password, this.roles);
            this.user = new BackendUser();
            endConversation();
            return "/home";
        } catch (FeatureNotSupportedException ex) {
            this.messages.error("FeatureNotSupportedException");
        } catch (IdentityException ex) {
            this.messages.error("IdentityException");
        }
        return null;

    }

    /**
     * Verify if the password is equal to the confirm password
     * @return 
     */
    private boolean verifyConfirmPassword() {
        if (!this.password.equals(this.confirmPassword)) {
            this.messages.error("Les deux mots de passe ne sont pas identiques.");
            return true;
        }
        return false;
    }

    /**
     * Save Role
     * @return 
     */
    public void saveRole() {
        roles.add(new SimpleRole(roleType, null, roleGroup));
    }

    /**
     * @return the user
     */
    public BackendUser getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(BackendUser user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the roleType
     */
    public RoleType getRoleType() {
        return roleType;
    }

    /**
     * @param roleType the roleType to set
     */
    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    /**
     * @return the roleGroup
     */
    public Group getRoleGroup() {
        return roleGroup;
    }

    /**
     * @param roleGroup the roleGroup to set
     */
    public void setRoleGroup(Group roleGroup) {
        this.roleGroup = roleGroup;
    }

    /**
     * @return the roles
     */
    public Collection<Role> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
