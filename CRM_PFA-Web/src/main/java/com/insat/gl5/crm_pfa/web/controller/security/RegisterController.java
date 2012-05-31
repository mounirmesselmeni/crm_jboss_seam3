/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.security;

import com.insat.gl5.crm_pfa.model.Contact;
import com.insat.gl5.crm_pfa.web.controller.ConversationController;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.seam.international.status.Messages;
import org.picketlink.idm.api.Group;
import org.picketlink.idm.api.Role;
import org.picketlink.idm.api.RoleType;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
@Named
@ConversationScoped
public class RegisterController extends ConversationController {

    private String activationCode;
    private boolean activationOk = false;
    @Inject
    private Messages messages;
    @Inject
    private UserManagement userManagement;
    @Inject
    private List<RoleType> lstRoleTypes;
    @Inject
    private List<Group> lstGroups;

    /**
     * Verifier le code d'activation
     */
    public void activate() {
        activationOk = activationCode.equals("2222");
    }

    /**
     * Valider le compte et création d'un accès pour le contact correspondant
     * @return 
     */
    public String validateAccount() {
        Contact contact = getContactFromActivationCode();
        this.userManagement.setRoles(new LinkedList<Role>());
        initClientRole();
        this.userManagement.saveRole();
        return this.userManagement.createClientAccount(contact);
    }

    /**
     * Init du rôle 'client' dans le groupe 'crm'
     */
    private void initClientRole() {
        for (RoleType roleType : lstRoleTypes) {
            if (roleType.getName().equals("client")) {
                this.userManagement.setRoleType(roleType);
            }
        }
        for (Group group : lstGroups) {
            if (group.getName().equals("crm")) {
                this.userManagement.setRoleGroup(group);
            }
        }
    }

    /**
     * @return the activationCode
     */
    public String getActivationCode() {
        return activationCode;
    }

    /**
     * @param activationCode the activationCode to set
     */
    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    /**
     * @return the activationOk
     */
    public boolean isActivationOk() {
        return activationOk;
    }

    /**
     * @param activationOk the activationOk to set
     */
    public void setActivationOk(boolean activationOk) {
        this.activationOk = activationOk;
    }

    private Contact getContactFromActivationCode() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
