/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service.security;

import com.insat.gl5.crm_pfa.model.Contact;
import com.insat.gl5.crm_pfa.model.security.IdentityObject;
import com.insat.gl5.crm_pfa.model.security.IdentityRoleName;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.TransactionAttribute;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.jboss.seam.security.GroupImpl;
import org.jboss.seam.security.Identity;
import org.jboss.solder.logging.Logger;
import org.picketlink.idm.api.Group;
import org.picketlink.idm.api.IdentitySession;
import org.picketlink.idm.api.Role;
import org.picketlink.idm.api.User;
import org.picketlink.idm.common.exception.FeatureNotSupportedException;
import org.picketlink.idm.common.exception.IdentityException;


/**
 *
 * @author Mounir Messelmeni, contact: messelmeni.mounir@gmail.com
 */
@TransactionAttribute
@Interceptors(org.jboss.seam.transaction.TransactionInterceptor.class)
public class UserProfileService implements Serializable {

    @Inject
    private EntityManager em;
    @Inject
    private Identity ident;
    @Inject
    private IdentitySession identitySession;
    @Inject
    private Logger log;
    @Inject
    private Event<Contact> uDetailsEvent;
    private final static String INFORMATION_USER = "informationUser";

    public boolean updatePassword(String password, String email, String oldPassword) {
        if (ident.getUser() != null) {
            this.log.info("Update password for user '" + email + "'. Done by " + ident.getUser().getId());
        } else {
            this.log.info("Update password for user '" + email + "'.");
        }
        User u = searchUser(email);
        try {
            if (!this.identitySession.getAttributesManager().validatePassword(u, oldPassword)) {
                return false;
            }
            this.identitySession.getAttributesManager().updatePassword(u, password);
            return true;
        } catch (IdentityException ex) {
            this.log.error("", ex);
            return false;
        }
    }

    public Contact saveNewUser(Contact contact, String password, Collection<Role> roles) throws FeatureNotSupportedException, IdentityException {
        if (this.ident.getUser() != null) {
            this.log.info("Save a new user '" + contact.getEmailAddress() + "'. Done by " + ident.getUser().getId());
        }
        User user = null;
        try {
            user = this.identitySession.getPersistenceManager().createUser(contact.getEmailAddress());
            this.identitySession.getAttributesManager().updatePassword(user, password);
            this.em.persist(contact.getEmailAddress());
            this.identitySession.getAttributesManager().addAttribute(user.getId(), INFORMATION_USER, contact.getId().toString());
            for (Role role : roles) {
                try {
                    identitySession.getRoleManager().createRole(role.getRoleType(), user, role.getGroup());
                } catch (FeatureNotSupportedException ex) {
                    this.log.error("FeatureNotSupportedException :", ex);
                    throw ex;
                }
            }
            return contact;
        } catch (IdentityException ex) {
            this.log.error("", ex);
            throw ex;
        }
    }

    //* find profiluser by id*/   
    public Contact getProfilById(int profilId) {
        return this.em.find(Contact.class, profilId);
    }

    /**
     * Edit User Profile
     * @param Contact 
     */
    public void editProfiluser(Contact contact) {
        this.log.info("Edit a profil '" + contact.getEmailAddress() + "'. Done by " + ident.getUser().getId());
        try {
            User user = searchUser(contact.getEmailAddress());
            String pu = this.identitySession.getAttributesManager().getAttribute(user, INFORMATION_USER).getValue().toString();
            contact.setId(Long.parseLong(pu));
            this.em.merge(contact);
        } catch (IdentityException ex) {
            this.log.error("", ex);
        }
        this.uDetailsEvent.fire(contact);
    }

    public Contact loadProfilUser(User user) {
        try {
            if (user != null && this.identitySession.getAttributesManager().getAttribute(user.getId(), INFORMATION_USER) != null) {
                Query query = this.em.createQuery("select p from Contact p where p.id = ?1");
                Object o = this.identitySession.getAttributesManager().getAttribute(user.getId(), INFORMATION_USER).getValue();
                query.setParameter(1, Long.parseLong(o.toString()));
                if (!query.getResultList().isEmpty()) {
                    return (Contact) query.getSingleResult();
                }
            }
        } catch (IdentityException ex) {
            this.log.error("", ex);
        }
        return null;
    }

    /**
     * Load profile by userId
     * @param userId
     * @return 
     */
    public Contact loadProfilUser(String userId) {
        try {
            Query query = this.em.createQuery("select p from Contact p where p.emailAddress = ?1");
            query.setParameter(1, userId);
            if (!query.getResultList().isEmpty()) {
                return (Contact) query.getSingleResult();
            }

        } catch (Exception ex) {
            this.log.error("", ex);
        }
        return null;
    }

    public User searchUser(String username) {
        try {
            if (this.identitySession.getPersistenceManager().findUser(username) != null) {
                return this.identitySession.getPersistenceManager().findUser(username);
            }
        } catch (IdentityException ex) {
            this.log.error("", ex);
        }
        return null;
    }

    public boolean userExists(String username) {
        return this.searchUser(username) != null;
    }

    public Contact verifyExistenceCount(String username) {
        Query query = this.em.createQuery("select p from Contact p where p.emailAddress = ?1");
        query.setParameter(1, username);
        if ((query.getResultList()).isEmpty()) {
            return null;
        }
        return (Contact) query.getSingleResult();
    }

    public Contact createContact() {
        return new Contact();
    }

    public void deleteGroup(String name, String groupType) throws IdentityException {
        Group group = new GroupImpl(name, groupType);
        this.identitySession.getPersistenceManager().removeGroup(group, true);
    }

    public void saveGroup(String groupName, String groupType) throws IdentityException {
        this.identitySession.getPersistenceManager().createGroup(groupName, groupType);
    }

    public void updateGroup(String oldName, String newName) {
        Query query = em.createQuery("select i from IdentityObject i where i.name=?1");
        query.setParameter(1, oldName);
        if ((query.getResultList()).isEmpty()) {
            return;
        }
        IdentityObject identityObject = (IdentityObject) query.getSingleResult();
        identityObject.setName(newName);
        em.persist(identityObject);
    }

    public void deleteRoleType(String roleType) throws IdentityException, FeatureNotSupportedException {
        this.identitySession.getRoleManager().removeRoleType(roleType);
    }

    public void saveRoleType(String roleType) throws IdentityException, FeatureNotSupportedException {
        this.identitySession.getRoleManager().createRoleType(roleType);
    }

    public void updateRoleType(String oldName, String newName) {
        Query query = em.createQuery("select i from IdentityRoleName i where i.name=?1");
        query.setParameter(1, oldName);
        if ((query.getResultList()).isEmpty()) {
            return;
        }
        IdentityRoleName identityRoleName = (IdentityRoleName) query.getSingleResult();
        identityRoleName.setName(newName);
        em.persist(identityRoleName);
    }
}
