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
import org.picketlink.idm.common.exception.IdentityException;

/**
 *
 * @author Mounir Messelmeni, contact: messelmeni.mounir@gmail.com
 */
@Named
@ConversationScoped
public class GroupController extends ConversationController {

    @Inject
    private UserProfileService userProfileService;
    @Inject
    private Messages messages;
    private String groupName;
    //Var used for update ( like an id for the old one )
    private String oldName;
    private static final String GROUP_TYPE = "GROUP";

    /**
     * Delete Group
     * @param groupName 
     */
    public void deleteGroup() {
        try {
            this.userProfileService.deleteGroup(this.groupName, GroupController.GROUP_TYPE);
            this.messages.info("Groupe {0} est supprimé", groupName);
            this.groupName = null;
        } catch (IdentityException ex) {
            this.messages.error("IdentityException : " + ex.getMessage());
        }
    }

    /**
     * Saving Group
     */
    public String saveGroup() {
        try {
            this.userProfileService.saveGroup(this.groupName, GroupController.GROUP_TYPE);
            this.messages.info("Groupe {0} est ajouté", groupName);
            this.groupName = null;
            return "listGroups.jsf?faces-redirect=true";
        } catch (IdentityException ex) {
            this.messages.error("IdentityException : " + ex.getMessage());
            return null;
        }
    }

    /**
     * Update Group
     */
    public void updateGroup() {
        this.userProfileService.updateGroup(this.oldName, this.groupName);
        this.messages.info("Groupe {0} est mis à jour", groupName);
        this.groupName = null;
    }

    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
