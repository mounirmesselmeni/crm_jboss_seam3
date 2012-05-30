/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.account;

import com.insat.gl5.crm_pfa.model.Account;
import com.insat.gl5.crm_pfa.model.Address;
import com.insat.gl5.crm_pfa.model.EmailAdress;
import com.insat.gl5.crm_pfa.model.PhoneNumber;
import com.insat.gl5.crm_pfa.service.AccountService;
import com.insat.gl5.crm_pfa.service.CoordinatesService;
import com.insat.gl5.crm_pfa.web.controller.ConversationController;
import com.insat.gl5.crm_pfa.web.controller.FileUploadController;
import com.insat.gl5.crm_pfa.web.viewModel.AddressViewModel;
import com.insat.gl5.crm_pfa.web.viewModel.EmailViewModel;
import com.insat.gl5.crm_pfa.web.viewModel.PhoneNumberViewModel;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.seam.international.status.Messages;

/**
 *
 * @author
 * Mu7ammed
 * 3li
 * --
 * mohamed.ali.affes@gmail.com
 * --
 */
@Named
@ConversationScoped
public class AccountController extends ConversationController {

    private final String ACCOUNTS_DIRECTORY = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/CRMData/accounts/") + "/";
    @Inject
    private AccountService accountService;
    @Inject
    private CoordinatesService coordinatesService;
    @Inject
    private FileUploadController fileUploadController;
    @Inject
    private Messages messages;
    private boolean editMode;
//    @Inject
    private Account account = new Account();
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

    public void createViewModels() {
        beginConversation();
        fileUploadController.resetFile();
        lstEmailViewModels = new LinkedList<EmailViewModel>();
        lstAddressViewModels = new LinkedList<AddressViewModel>();
        lstPhoneNumberViewModels = new LinkedList<PhoneNumberViewModel>();

        lstEmailViewModels.add(new EmailViewModel(0, new EmailAdress()));
        lstAddressViewModels.add(new AddressViewModel(0, new Address()));
        lstPhoneNumberViewModels.add(new PhoneNumberViewModel(0, new PhoneNumber()));
    }

    public void initViewModels(Account account) {
        beginConversation();
        fileUploadController.resetFile();
        setAccount(account);
        initEmailViewModels();
        initAddressViewModels();
        initPhoneNumberViewModels();
    }

    private void initEmailViewModels() {
        lstEmailViewModels = new LinkedList<EmailViewModel>();
        int index = 0;
        for (EmailAdress email : account.getLstEmails()) {
            lstEmailViewModels.add(new EmailViewModel(index++, email));
        }
    }

    private void initAddressViewModels() {
        lstAddressViewModels = new LinkedList<AddressViewModel>();
        int index = 0;
        for (Address address : account.getLstAddresses()) {
            lstAddressViewModels.add(new AddressViewModel(index++, address));
        }
    }

    private void initPhoneNumberViewModels() {
        lstPhoneNumberViewModels = new LinkedList<PhoneNumberViewModel>();
        int index = 0;
        for (PhoneNumber number : account.getLstPhoneNumbers()) {
            lstPhoneNumberViewModels.add(new PhoneNumberViewModel(index++, number));
        }
    }

    /**
     * Save
     * an
     * account
     *
     */
    public String saveAccount() {

        try {

            affectCoordinates();
            account.setLogoURL(ACCOUNTS_DIRECTORY + account.getName() + ".png");
            accountService.saveAccount(getAccount());
            uploadLogo();
            messages.info("Compte {0} est enregistré avec succés !", getAccount().getName());
            setAccount(null);

        } catch (Exception e) {
            messages.error("Erreur d'enregistrement du compte {0}", getAccount().getName());
            return null;
        }
        endConversation();
        return getRedirect();
    }

    /**
     * Edit
     * an
     * account
     *
     */
    public String editAccount() {

        try {
            editCoordinates();
            //Mettre à jour le nom du logo
            updateLogo();
            accountService.editAccount(getAccount());
            messages.info("Compte {0} est modifié avec succés !", getAccount().getName());

            setAccount(null);

        } catch (Exception e) {
            messages.error("Erreur de modification du compte {0}", getAccount().getName());
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
                account.getLstEmails().remove(emailAdress);
            } catch (Exception ex) {
                messages.error("Erreur de supression de l'e-mail {0}", emailAdress);
            }
        }
        for (EmailAdress emailAdress : lstEmailsToAdd) {
            try {
                coordinatesService.saveEmailAdress(emailAdress);
                account.getLstEmails().add(emailAdress);
            } catch (Exception ex) {
                messages.error("Erreur de l'ajout de l'e-mail {0}", emailAdress);
            }
        }
    }

    private void editAddresses() {
        for (Address address : lstAddressesToDelete) {
            try {
                coordinatesService.deleteAddress(address);
                account.getLstAddresses().remove(address);
            } catch (Exception ex) {
                messages.error("Erreur de supression de l'adresse {0}", address);
            }
        }
        for (Address address : lstAddressesToAdd) {
            try {
                coordinatesService.saveAddress(address);
                account.getLstAddresses().add(address);
            } catch (Exception ex) {
                messages.error("Erreur de l'ajout de l'adresse {0}", address);
            }

        }
    }

    private void editPhoneNumbers() {
        for (PhoneNumber number : lstPhoneNumbersToDelete) {
            try {
                coordinatesService.deletePhoneNumber(number);
                account.getLstPhoneNumbers().remove(number);
            } catch (Exception ex) {
                messages.error("Erreur de supression du numéro {0}", number);
            }
        }

        for (PhoneNumber number : lstPhoneNumbersToAdd) {
            try {
                coordinatesService.savePhoneNumber(number);
                account.getLstPhoneNumbers().add(number);
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
    public void deleteAccount() {

        try {
            deleteLogo();
            accountService.deleteAccount(getAccount());
            messages.info("Compte {0} est supprimé avec succés !", getAccount().getName());

            setAccount(null);

        } catch (Exception e) {
            messages.error("Erreur de supression du compte {0}", getAccount().getName());
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
        account.setLstEmails(lstEmails);
    }

    private void affectPhoneNumbers() {
        List<PhoneNumber> lstPhoneNumbers = new LinkedList<PhoneNumber>();
        for (PhoneNumberViewModel model : lstPhoneNumberViewModels) {
            lstPhoneNumbers.add(model.getPhoneNumber());
        }
        account.setLstPhoneNumbers(lstPhoneNumbers);
    }

    private void affectAddresses() {
        List<Address> lstAddresses = new LinkedList<Address>();
        for (AddressViewModel model : lstAddressViewModels) {
            lstAddresses.add(model.getAddress());
        }
        account.setLstAddresses(lstAddresses);

    }

    private void deleteLogo() throws IOException {
        // supprimer le logo
        File logo = new File(account.getLogoURL());
        logo.delete();
    }

    private void updateLogo() {
        // Nouveau chemin
        String newPath = ACCOUNTS_DIRECTORY + account.getName() + ".png";
        // Ancien chemin
        String lastPath = account.getLogoURL();
        // Mettre à jour le  chemin
        if (!newPath.equalsIgnoreCase(lastPath)) {
            File lastURL = new File(lastPath);
            File newURL = new File(newPath);
            lastURL.renameTo(newURL);
            account.setLogoURL(newPath);
        }
    }

    private void uploadLogo() throws IOException {
        if (fileUploadController.getFile() != null) {
            fileUploadController.upload(account.getLogoURL());
        } else {
            fileUploadController.uploadFromURL("//resources//images//other//defaultLogo.png", account.getLogoURL());
        }
    }

    /**
     * @return
     * the
     * account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * @param
     * account
     * the
     * account
     * to
     * set
     */
    public void setAccount(Account account) {
        this.account = account;
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
        this.setLstAddressViewModels(lstAddressViewModels);
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
}
