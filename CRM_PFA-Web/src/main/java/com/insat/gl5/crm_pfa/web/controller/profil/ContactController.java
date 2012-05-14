/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.profil;

import com.insat.gl5.crm_pfa.model.Contact;
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
import org.picketlink.idm.api.User;
import org.picketlink.idm.common.exception.FeatureNotSupportedException;
import org.picketlink.idm.common.exception.IdentityException;
import org.picketlink.idm.impl.api.model.SimpleRole;

/**
 *
 * @author Mounir Messelmeni, contact: messelmeni.mounir@gmail.com
 */
@Named
@ConversationScoped
public class ContactController extends ConversationController {

    @Inject
    private UserProfileService profileService;
    //@Inject
    private Contact contact = new Contact();
    private String password = null, confirmPassword = null;
    private Collection<Role> roles = new LinkedList<Role>();
    private RoleType roleType;
    private Group roleGroup;
    @Inject
    private Messages messages;

    /**
     * Save a new User
     */
    public String saveUser() {
        if (verifyConfirmPassword()) {
            return null;
        }
        convertEmailToLowerCase();
        if (isUserExist()) {
            return null;
        }
        if (saveNewUser()) {
            return null;
        }
        this.endConversation();
        this.messages.info("Utilisateur {0} {1} est créé", this.getContact().getFirstName(), this.getContact().getLastName());
        return "listUsers";
    }

    /**
     * convert userDetails Email To LowerCase
     */
    private void convertEmailToLowerCase() {
        this.getContact().setEmailAddress(this.getContact().getEmailAddress().toLowerCase());
    }

    /**
     * Save a new user
     * @return 
     */
    private boolean saveNewUser() {
        try {
            this.profileService.saveNewUser(this.getContact(), this.password, roles);
        } catch (FeatureNotSupportedException ex) {
            this.messages.error("FeatureNotSupportedException : " + ex.getMessage());
            return true;
        } catch (IdentityException ex) {
            this.messages.error("Identity Exception : " + ex.getMessage());
            return true;
        }
        return false;
    }

    /**
     * Verify if user exist
     * @return 
     */
    private boolean isUserExist() {
        User u = this.profileService.searchUser(this.getContact().getEmailAddress());
        if (u != null) {
            this.messages.error("Utilisateur existe déja");
            return true;
        }
        return false;
    }

    /**
     * Verify if the password is equal to the confirm password
     * @return 
     */
    private boolean verifyConfirmPassword() {
        if (!this.password.equals(this.confirmPassword)) {
            this.messages.error("Mot de passe invalide");
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
     * @return the contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
