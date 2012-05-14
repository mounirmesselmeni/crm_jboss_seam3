/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.profil;

import com.insat.gl5.crm_pfa.service.security.UserProfileService;
import com.insat.gl5.crm_pfa.web.controller.ConversationController;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.seam.international.status.Messages;
import org.picketlink.idm.common.exception.FeatureNotSupportedException;
import org.picketlink.idm.common.exception.IdentityException;

/**
 *
 * @author Mounir Messelmeni, contact: messelmeni.mounir@gmail.com
 */
@Named
@ConversationScoped
public class RoleTypeController extends ConversationController {

    @Inject
    private UserProfileService userProfileService;
    @Inject
    private Messages messages;
    private String roleType;
    //Var used for update ( like an id for the old one )
    private String oldName;

    /**
     * Delete a role type
     * @param roleType 
     */
    public void deleteRoleType() {
        try {
            this.userProfileService.deleteRoleType(this.roleType);
            this.messages.info("Role {0} est supprimé", this.roleType);
            this.roleType = null;
        } catch (IdentityException ex) {
            this.messages.error("IdentityException : " + ex.getMessage());
        } catch (FeatureNotSupportedException ex) {
            this.messages.error("FeatureNotSupportedException : " + ex.getMessage());
        }
    }

    /**
     * Saving role type
     */
    public String save() {
        try {
            this.userProfileService.saveRoleType(roleType);
            this.messages.info("Role {0} est ajouté", roleType);
            this.roleType = null;
            return "listRoleTypes.jsf?faces-redirect=true";
        } catch (IdentityException ex) {
            this.messages.error("IdentityException : " + ex.getMessage());
            return null;
        } catch (FeatureNotSupportedException ex) {
            this.messages.error("FeatureNotSupportedException : " + ex.getMessage());
            return null;
        }
    }

    /**
     * Update Role type's name
     */
    public void updateRoleType() {
        this.userProfileService.updateRoleType(this.oldName, this.roleType);
        this.messages.info("Role {0} est mis à jour", roleType);
        this.roleType = null;
    }

    /**
     * @return the roleType
     */
    public String getRoleType() {
        return roleType;
    }

    /**
     * @param roleType the roleType to set
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    /**
     * @return the oldName
     */
    public String getOldName() {
        return oldName;
    }

    /**
     * @param oldName the oldName to set
     */
    public void setOldName(String oldName) {
        this.oldName = oldName;
    }
}
