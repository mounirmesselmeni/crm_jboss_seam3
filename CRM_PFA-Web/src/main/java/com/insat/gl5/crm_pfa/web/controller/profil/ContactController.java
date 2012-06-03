/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.profil;

import com.insat.gl5.crm_pfa.enumeration.Salutation;
import com.insat.gl5.crm_pfa.model.*;
import com.insat.gl5.crm_pfa.service.ContactService;
import com.insat.gl5.crm_pfa.service.CoordinatesService;
import com.insat.gl5.crm_pfa.service.mail.MailService;
import com.insat.gl5.crm_pfa.service.mail.Person;
import com.insat.gl5.crm_pfa.service.security.UserProfileService;
import com.insat.gl5.crm_pfa.web.controller.ConversationController;
import com.insat.gl5.crm_pfa.web.controller.FileUploadController;
import com.insat.gl5.crm_pfa.web.exception.ExistingEmailException;
import com.insat.gl5.crm_pfa.web.exception.ExistingLoginException;
import com.insat.gl5.crm_pfa.web.viewModel.AddressViewModel;
import com.insat.gl5.crm_pfa.web.viewModel.EmailViewModel;
import com.insat.gl5.crm_pfa.web.viewModel.PhoneNumberViewModel;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
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
 * @author
 * Mounir
 * Messelmeni,
 * contact:
 * messelmeni.mounir@gmail.com
 */
@Named
@ConversationScoped
public class ContactController extends ConversationController {

    private final String CONTACTS_DIRECTORY = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/CRMData/contacts/") + "/";
    @Inject
    private UserProfileService profileService;
    @Inject
    private ContactService contactService;
    @Inject
    private CoordinatesService coordinatesService;
    @Inject
    private MailService mailService;
    @Inject
    private FileUploadController fileUploadController;
    private boolean editMode;
    @Inject
    private Contact contact;
    private String password = null, confirmPassword = null;
    private Collection<Role> roles = new LinkedList<Role>();
    private RoleType roleType;
    private Group roleGroup;
    private String lastEmail;
    private String lastLogin;
    @Inject
    private Messages messages;
    private String redirect;
    private List<EmailViewModel> lstEmailViewModels;
    private List<AddressViewModel> lstAddressViewModels;
    private List<PhoneNumberViewModel> lstPhoneNumberViewModels;
    private List<EmailAdress> lstEmailsToDelete = new LinkedList<EmailAdress>();
    private List<EmailAdress> lstEmailsToAdd = new LinkedList<EmailAdress>();
    private List<Address> lstAddressesToDelete = new LinkedList<Address>();
    private List<Address> lstAddressesToAdd = new LinkedList<Address>();
    private List<PhoneNumber> lstPhoneNumbersToDelete = new LinkedList<PhoneNumber>();
    private List<PhoneNumber> lstPhoneNumbersToAdd = new LinkedList<PhoneNumber>();

    /**
     * Save
     * a
     * new
     * User
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
        /*
         * ----
         */
        endConversation();
        return getRedirect();
    }

    /**
     * convert
     * userDetails
     * Email
     * To
     * LowerCase
     */
    private void convertEmailToLowerCase() {
//        this.getContact().setEmailAddress(this.getContact().getLogin().toLowerCase());
    }

    /**
     * Save
     * a
     * new
     * user
     *
     * @return
     */
    private boolean saveNewUser() {
        try {
            affectCoordinates();
            contact.setImageURL(contact.getLogin() + ".png");
            this.profileService.saveNewUser(this.getContact(), this.password, roles);
            uploadLogo();
        } catch (IOException ex) {
            Logger.getLogger(ContactController.class.getName()).log(Level.SEVERE, null, ex);
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
     * Verify
     * if
     * user
     * exist
     *
     * @return
     */
    private boolean isUserExist() {
        User u = this.profileService.searchUser(this.getContact().getLogin());
        if (u != null) {
            this.messages.error("Utilisateur existe déja");
            return true;
        }
        return false;
    }

    /**
     * Verify
     * if
     * the
     * password
     * is
     * equal
     * to
     * the
     * confirm
     * password
     *
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
     * Save
     * Role
     *
     * @return
     */
    public void saveRole() {
        roles.add(new SimpleRole(roleType, null, roleGroup));
    }

    /**
     * Save
     * an
     * account
     *
     */
    public String saveContact() {

        try {
            validateAttributes();
            affectCoordinates();
            contact.setImageURL(contact.getLogin() + ".png");
            contactService.saveContact(getContact());
            uploadLogo();
            messages.info("Contact {0} est enregistré avec succés !", getContact());
            ActivationCode activationCode = new ActivationCode(contact);
            contactService.saveActivationCode(activationCode);
            Person receiver = new Person(contact.getLastName(), contact.getLstEmails().get(0).getValue());
            mailService.sendJoinInvitation(receiver, activationCode.getCode(), contact.getLogin());
            setContact(null);
        } catch (ExistingLoginException e) {
            messages.error("Login : {0} existe déja !", getContact().getLogin());
            return null;
        } catch (ExistingEmailException e) {
            messages.error("L'e-mail : {0} existe déja !", lstEmailViewModels.get(0).getEmail().getValue());
            return null;
        } catch (MalformedURLException ex) {
            messages.error("Erreur d'envoi mail");
            System.out.println("--->" + ex.getMessage());
            return null;
        } catch (Exception e) {
            messages.error("Erreur d'enregistrement du contact {0}", getContact());
            System.out.println("--->" + e.getMessage());
            return null;
        }
        endConversation();
        return getRedirect();
    }

    /**
     * -------------------------------------
     */
    public void createViewModels() {
        beginConversation();
        fileUploadController.resetFile();
        contact.setSalutation(Salutation.MR);
        lstEmailViewModels = new LinkedList<EmailViewModel>();
        lstAddressViewModels = new LinkedList<AddressViewModel>();
        lstPhoneNumberViewModels = new LinkedList<PhoneNumberViewModel>();

        lstEmailViewModels.add(new EmailViewModel(0, new EmailAdress()));
        lstAddressViewModels.add(new AddressViewModel(0, new Address()));
        lstPhoneNumberViewModels.add(new PhoneNumberViewModel(0, new PhoneNumber()));
    }

    public void initViewModels(Contact contact) {
        beginConversation();
        fileUploadController.resetFile();
        setContact(contact);
        lastLogin = contact.getLogin();
        contact.setAccount(contactService.getAccountByContact(contact));
        initEmailViewModels();
        initAddressViewModels();
        initPhoneNumberViewModels();
    }

    private void initEmailViewModels() {
        lstEmailViewModels = new LinkedList<EmailViewModel>();
        lastEmail = contact.getLstEmails().get(0).getValue();
        int index = 0;
        for (EmailAdress email : contact.getLstEmails()) {
            lstEmailViewModels.add(new EmailViewModel(index++, email));
        }
    }

    private void initAddressViewModels() {
        lstAddressViewModels = new LinkedList<AddressViewModel>();
        int index = 0;
        for (Address address : contact.getLstAddresses()) {
            lstAddressViewModels.add(new AddressViewModel(index++, address));
        }
    }

    private void initPhoneNumberViewModels() {
        lstPhoneNumberViewModels = new LinkedList<PhoneNumberViewModel>();
        int index = 0;
        for (PhoneNumber number : contact.getLstPhoneNumbers()) {
            lstPhoneNumberViewModels.add(new PhoneNumberViewModel(index++, number));
        }
    }

    private void validateAttributes() throws ExistingEmailException, ExistingLoginException {

        if (contactService.loginExits(contact.getLogin())) {
            if (!lastLogin.equals(contact.getLogin())) {
                throw new ExistingLoginException();
            }
        }

        if (coordinatesService.emailExits(lstEmailViewModels.get(0).getEmail().getValue())) {
            if (!lastEmail.equals(lstEmailViewModels.get(0).getEmail().getValue())) {
                throw new ExistingEmailException();
            }
        }
    }

    /**
     * Edit
     * an
     * account
     *
     */
    public String editContact() {

        try {
            validateAttributes();
            editCoordinates();
            //Mettre à jour le nom du logo
            updateLogo();
            contactService.editContact(getContact());
            messages.info("Contact {0} est modifié avec succés !", getContact());

            setContact(null);
        } catch (ExistingLoginException e) {
            messages.error("Login : {0} existe déja !", getContact().getLogin());
            return null;
        } catch (ExistingEmailException e) {
            messages.error("L'e-mail : {0} existe déja !", lstEmailViewModels.get(0).getEmail().getValue());
            return null;
        } catch (Exception e) {
            messages.error("Erreur de modification du contact {0}", getContact());
            return null;
        }
        endConversation();
        return getRedirect();
    }

    private void editCoordinates() {
        editEmails();
        editAddresses();
        editPhoneNumbers();
    }

    private void editEmails() {
        for (EmailAdress emailAdress : lstEmailsToDelete) {
            try {
                coordinatesService.deleteEmailAdress(emailAdress);
                contact.getLstEmails().remove(emailAdress);
            } catch (Exception ex) {
                messages.error("Erreur de supression de l'e-mail {0}", emailAdress);
            }
        }
        for (EmailAdress emailAdress : lstEmailsToAdd) {
            try {
                coordinatesService.saveEmailAdress(emailAdress);
                contact.getLstEmails().add(emailAdress);
            } catch (Exception ex) {
                messages.error("Erreur de l'ajout de l'e-mail {0}", emailAdress);
            }
        }
    }

    private void editAddresses() {
        for (Address address : lstAddressesToDelete) {
            try {
                coordinatesService.deleteAddress(address);
                contact.getLstAddresses().remove(address);
            } catch (Exception ex) {
                messages.error("Erreur de supression de l'adresse {0}", address);
            }
        }
        for (Address address : lstAddressesToAdd) {
            try {
                coordinatesService.saveAddress(address);
                contact.getLstAddresses().add(address);
            } catch (Exception ex) {
                messages.error("Erreur de l'ajout de l'adresse {0}", address);
            }

        }
    }

    private void editPhoneNumbers() {
        for (PhoneNumber number : lstPhoneNumbersToDelete) {
            try {
                coordinatesService.deletePhoneNumber(number);
                contact.getLstPhoneNumbers().remove(number);
            } catch (Exception ex) {
                messages.error("Erreur de supression du numéro {0}", number);
            }
        }

        for (PhoneNumber number : lstPhoneNumbersToAdd) {
            try {
                coordinatesService.savePhoneNumber(number);
                contact.getLstPhoneNumbers().add(number);
            } catch (Exception ex) {
                messages.error("Erreur de l'ajout du numéro {0}", number);
            }
        }
    }

    /**
     * Delete
     * the
     * selected
     * account
     */
    public void deleteContact() {

        try {
            deleteLogo();
            contactService.deleteContact(getContact());
            messages.info("Contact {0} est supprimé avec succés !", getContact());

            setContact(null);

        } catch (Exception e) {
            messages.error("Erreur de supression du contact {0}", getContact());
        }
        endConversation();
    }

    public void removeEmail(int index) {
        EmailAdress emailAdress = lstEmailViewModels.get(index).getEmail();
        if (emailAdress.isPersistent()) {
            lstEmailsToDelete.add(emailAdress);
        }
        lstEmailViewModels.remove(index);
        updateEmailIndexes(index);
        if (lstEmailsToAdd.contains(emailAdress)) {
            lstEmailsToAdd.remove(emailAdress);
        }
    }

    public void addEmail() {
        EmailAdress email = new EmailAdress();
        lstEmailViewModels.add(new EmailViewModel(lstEmailViewModels.size(), email));
        lstEmailsToAdd.add(email);
    }

    public void removePhoneNumber(int index) {
        PhoneNumber phoneNumber = lstPhoneNumberViewModels.get(index).getPhoneNumber();
        if (phoneNumber.isPersistent()) {
            lstPhoneNumbersToDelete.add(phoneNumber);
        }
        lstPhoneNumberViewModels.remove(index);
        updatePhoneNumberIndexes(index);
        if (lstPhoneNumbersToAdd.contains(phoneNumber)) {
            lstPhoneNumbersToAdd.remove(phoneNumber);
        }
    }

    public void addPhoneNumber() {
        PhoneNumber phoneNumber = new PhoneNumber();
        lstPhoneNumberViewModels.add(new PhoneNumberViewModel(lstPhoneNumberViewModels.size(), phoneNumber));
        lstPhoneNumbersToAdd.add(phoneNumber);
    }

    public void removeAddress(int index) {
        Address address = lstAddressViewModels.get(index).getAddress();
        if (address.isPersistent()) {
            lstAddressesToDelete.add(address);
        }
        lstAddressViewModels.remove(index);
        updateAddressIndexes(index);
        if (lstAddressesToAdd.contains(address)) {
            lstAddressesToAdd.remove(address);
        }
    }

    public void addAddress() {
        Address address = new Address();
        lstAddressViewModels.add(new AddressViewModel(lstAddressViewModels.size(), address));
        lstAddressesToAdd.add(address);
    }

    /**
     * Update
     * index
     * of
     * the
     * list
     */
    private void updateEmailIndexes(int index) {

        for (EmailViewModel model : lstEmailViewModels.subList(index, lstEmailViewModels.size())) {
            model.setIndex(model.getIndex() - 1);
        }
    }

    /**
     * Update
     * index
     * of
     * the
     * list
     */
    private void updateAddressIndexes(int index) {

        for (AddressViewModel model : lstAddressViewModels.subList(index, lstAddressViewModels.size())) {
            model.setIndex(model.getIndex() - 1);
        }
    }

    /**
     * Update
     * index
     * of
     * the
     * list
     */
    private void updatePhoneNumberIndexes(int index) {
        for (PhoneNumberViewModel model : lstPhoneNumberViewModels.subList(index, lstPhoneNumberViewModels.size())) {
            model.setIndex(model.getIndex() - 1);
        }
    }

    private void affectCoordinates() {
        affectEmails();
        affectPhoneNumbers();
        affectAddresses();
    }

    private void affectEmails() {
        List<EmailAdress> lstEmails = new LinkedList<EmailAdress>();
        for (EmailViewModel model : lstEmailViewModels) {
            lstEmails.add(model.getEmail());
        }
        contact.setLstEmails(lstEmails);
    }

    private void affectPhoneNumbers() {
        List<PhoneNumber> lstPhoneNumbers = new LinkedList<PhoneNumber>();
        for (PhoneNumberViewModel model : lstPhoneNumberViewModels) {
            lstPhoneNumbers.add(model.getPhoneNumber());
        }
        contact.setLstPhoneNumbers(lstPhoneNumbers);
    }

    private void affectAddresses() {
        List<Address> lstAddresses = new LinkedList<Address>();
        for (AddressViewModel model : lstAddressViewModels) {
            lstAddresses.add(model.getAddress());
        }
        contact.setLstAddresses(lstAddresses);

    }

    private void deleteLogo() throws IOException {
        // supprimer le logo
        File logo = new File(contact.getImageURL());
        logo.delete();
    }

    private void updateLogo() {
        // Nouveau chemin
        String newPath = contact.getLogin() + ".png";
        // Ancien chemin
        String lastPath = contact.getImageURL();
        // Mettre à jour le  chemin
        if (!newPath.equalsIgnoreCase(lastPath)) {
            File lastURL = new File(CONTACTS_DIRECTORY + lastPath);
            File newURL = new File(CONTACTS_DIRECTORY + newPath);
            lastURL.renameTo(newURL);
            contact.setImageURL(newPath);
        }
    }

    private void uploadLogo() throws IOException {
        // Création du répertoire du client
        File dir = new File(CONTACTS_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (fileUploadController.getFile() != null) {
            fileUploadController.upload(CONTACTS_DIRECTORY + contact.getImageURL());
        } else {
            fileUploadController.uploadFromURL("//resources//images//other//defaultLogo.png", CONTACTS_DIRECTORY + contact.getImageURL());
        }
    }

    /**
     * @return
     * the
     * password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param
     * password
     * the
     * password
     * to
     * set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return
     * the
     * confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param
     * confirmPassword
     * the
     * confirmPassword
     * to
     * set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * @return
     * the
     * roles
     */
    public Collection<Role> getRoles() {
        return roles;
    }

    /**
     * @param
     * roles
     * the
     * roles
     * to
     * set
     */
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    /**
     * @return
     * the
     * roleType
     */
    public RoleType getRoleType() {
        return roleType;
    }

    /**
     * @param
     * roleType
     * the
     * roleType
     * to
     * set
     */
    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    /**
     * @return
     * the
     * roleGroup
     */
    public Group getRoleGroup() {
        return roleGroup;
    }

    /**
     * @param
     * roleGroup
     * the
     * roleGroup
     * to
     * set
     */
    public void setRoleGroup(Group roleGroup) {
        this.roleGroup = roleGroup;
    }

    /**
     * @return
     * the
     * contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * @param
     * contact
     * the
     * contact
     * to
     * set
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * @return
     * the
     * editMode
     */
    public boolean isEditMode() {
        return editMode;
    }

    /**
     * @param
     * editMode
     * the
     * editMode
     * to
     * set
     */
    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    /**
     * @return
     * the
     * lstEmailViewModels
     */
    public List<EmailViewModel> getLstEmailViewModels() {
        return lstEmailViewModels;
    }

    /**
     * @param
     * lstEmailViewModels
     * the
     * lstEmailViewModels
     * to
     * set
     */
    public void setLstEmailViewModels(List<EmailViewModel> lstEmailViewModels) {
        this.lstEmailViewModels = lstEmailViewModels;
    }

    /**
     * @return
     * the
     * lstAddressViewModels
     */
    public List<AddressViewModel> getLstAddressViewModels() {
        return lstAddressViewModels;
    }

    /**
     * @param
     * lstAddressViewModels
     * the
     * lstAddressViewModels
     * to
     * set
     */
    public void setLstAddressViewModels(List<AddressViewModel> lstAddressViewModels) {
        this.lstAddressViewModels = lstAddressViewModels;
    }

    /**
     * @return
     * the
     * lstPhoneNumberViewModels
     */
    public List<PhoneNumberViewModel> getLstPhoneNumberViewModels() {
        return lstPhoneNumberViewModels;
    }

    /**
     * @param
     * lstPhoneNumberViewModels
     * the
     * lstPhoneNumberViewModels
     * to
     * set
     */
    public void setLstPhoneNumberViewModels(List<PhoneNumberViewModel> lstPhoneNumberViewModels) {
        this.lstPhoneNumberViewModels = lstPhoneNumberViewModels;
    }

    /**
     * @return
     * the
     * redirect
     */
    public String getRedirect() {
        return redirect;
    }

    /**
     * @param
     * redirect
     * the
     * redirect
     * to
     * set
     */
    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
