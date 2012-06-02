/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controlller.opportunity;

import com.insat.gl5.crm_pfa.enumeration.DirectionEnum;
import com.insat.gl5.crm_pfa.enumeration.NotificationType;
import com.insat.gl5.crm_pfa.enumeration.OpportunityType;
import com.insat.gl5.crm_pfa.model.*;
import com.insat.gl5.crm_pfa.service.AccountService;
import com.insat.gl5.crm_pfa.service.ContactService;
import com.insat.gl5.crm_pfa.service.NotificationService;
import com.insat.gl5.crm_pfa.service.OpportunityService;
import com.insat.gl5.crm_pfa.service.qualifier.CurrentUser;
import com.insat.gl5.crm_pfa.web.controller.ConversationController;
import com.insat.gl5.crm_pfa.web.exception.ContactRequiredException;
import com.insat.gl5.crm_pfa.web.exception.ProductsRequiredException;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.ConversationScoped;
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
public class OpportunityController extends ConversationController {

    @Inject
    private OpportunityService opportunityService;
    @Inject
    private AccountService accountService;
    @Inject
    private ContactService contactService;
    @Inject
    private NotificationService notificationService;
    @Inject
    private ItemsToPurchaseController itemsToPurchaseController;
    @Inject
    private Opportunity opportunity;
    @Inject
    private Messages messages;
    private String redirect;
    private Account selectedAccount;
    private List<Contact> selectedContacts;
    @Inject
    @CurrentUser
    private BackendUser currentUser;

    /**
     * Save
     * an
     * opportunity
     *
     */
    public String saveOpportunity() {

        try {
            validateContact();
            affectProducts();
            opportunity.setType(OpportunityType.PROPOSITION);
            opportunity.setAssignedTo(currentUser);
            opportunityService.saveOpportunity(getOpportunity());
            notifyContacts();
            messages.info("Opportunité {0} est enregistrée avec succés !", getOpportunity());
            setOpportunity(null);

        } catch (ContactRequiredException e) {
            messages.error("Veuillez choisir le contact");
            return null;
        } catch (ProductsRequiredException e) {
            messages.error("Veuillez choisir les produits");
            return null;
        } catch (Exception e) {
            messages.error("Erreur d'enregistrement de l'Opportunité {0}", getOpportunity());
            return null;
        }
        endConversation();
        return getRedirect();
    }

    /**
     * Edit
     * an
     * Opportunity
     *
     */
    public String editOpportunity() {

        try {
            validateContact();
            editProducts();

            opportunityService.editOpportunity(getOpportunity());
            messages.info("Opportunité {0} est modifiée avec succés !", getOpportunity());

            setOpportunity(null);
        } catch (ContactRequiredException e) {
            messages.error("Veuillez choisir le contact");
            return null;
        } catch (ProductsRequiredException e) {
            messages.error("Veuillez choisir les produits");
            return null;
        } catch (Exception e) {
            messages.error("Erreur de modification de l'Opportunité {0}", getOpportunity());
            return null;
        }
        endConversation();
        return getRedirect();
    }

    private void editProducts() throws ProductsRequiredException {
        if (itemsToPurchaseController.getItemsToPurchase().isEmpty()) {
            throw new ProductsRequiredException();
        }
        for (ItemToPurchase item : itemsToPurchaseController.getLstItemsToDelete()) {
            try {
                itemsToPurchaseController.deleteItem(item);
                opportunity.getItemsToPurchase().remove(item);
            } catch (Exception ex) {
                messages.error("Erreur de supression de l'item {0}", item);
            }
        }
        for (ItemToPurchase item : itemsToPurchaseController.getLstItemsToAdd()) {
            try {
                itemsToPurchaseController.saveItem(item);
                opportunity.getItemsToPurchase().add(item);
            } catch (Exception ex) {
                messages.error("Erreur de l'ajout de l'item {0}", item);
            }
        }
    }

    public void validateContact() throws ContactRequiredException {
        if (opportunity.getRelatedTo() == null) {
            throw new ContactRequiredException();
        }
    }

    public double calculateTotalPrice() {
        double price = 0;
        for (ItemToPurchase item : itemsToPurchaseController.getItemsToPurchase()) {
            price += item.getQuantity() * item.getProduct().getPrice();
        }
        return price;
    }

    public double calculateOpportunityPrice() {
        if (opportunity.getRelatedTo() == null) {
            return calculateTotalPrice();
        }
        return calculateTotalPrice() * ((100 - contactService.getFidelityByContact(opportunity.getRelatedTo()).getScore()) / 100);
    }

    public void initProducts() {
        beginConversation();
        itemsToPurchaseController.initProducts();
    }

    public void loadProducts(Opportunity o) {
        beginConversation();
        opportunity = o;
        selectedAccount = accountService.findById(opportunity.getRelatedTo().getAccount().getId());
        itemsToPurchaseController.loadProducts(opportunity.getItemsToPurchase());
    }

    /**
     * Delete
     * the
     * selected
     * account
     */
    public void deleteOpportunity() {

        try {
            notificationService.deleteNotification(notificationService.getNotificationByOpportunity(opportunity.getId()));
            opportunityService.deleteOpportunity(getOpportunity());
            messages.info("Opportunité {0} est supprimée avec succés !", getOpportunity());
            setOpportunity(null);
        } catch (Exception e) {
            messages.error("Erreur de supression de l'Opportunité {0}", getOpportunity());
        }
        endConversation();
    }

    private void affectProducts() throws ProductsRequiredException {
        if (itemsToPurchaseController.getItemsToPurchase().isEmpty()) {
            throw new ProductsRequiredException();
        }
        opportunity.setItemsToPurchase(itemsToPurchaseController.getItemsToPurchase());
    }

    private void notifyContacts() throws Exception {
        Notification notification = new Notification();
        notification.setContent("Vous avez un offre : <" + currentUser.getLogin() + "> vous invite à consulter l'opportunité ' " + opportunity.getName() + " ' !!");
        notification.setLink("/frontoffice/notifications/viewOpportunity?id=" + opportunity.getId());
        notification.setType(NotificationType.OPPORTUNITE);
        List<NotificationContact> list = new LinkedList<NotificationContact>();
        NotificationContact notificationContact = new NotificationContact(opportunity.getRelatedTo(), currentUser, notification, DirectionEnum.FROM_BACKUSER);
        list.add(notificationContact);
        notification.setNotificationContacts(list);
        notificationService.saveNotificationContact(notificationContact);
        notificationService.saveNotification(notification);
    }

    public List<Contact> getContactsByAccount() {
        if (selectedAccount == null) {
            return null;
        }
        return contactService.getContactsByAccount(accountService.findById(selectedAccount.getId()));
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
     * opportunity
     */
    public Opportunity getOpportunity() {
        return opportunity;
    }

    /**
     * @param
     * opportunity
     * the
     * opportunity
     * to
     * set
     */
    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }

    /**
     * @return
     * the
     * selectedAccount
     */
    public Account getSelectedAccount() {
        return selectedAccount;
    }

    /**
     * @param
     * selectedAccount
     * the
     * selectedAccount
     * to
     * set
     */
    public void setSelectedAccount(Account selectedAccount) {
        this.selectedAccount = selectedAccount;
    }

    /**
     * @return
     * the
     * selectedContacts
     */
    public List<Contact> getSelectedContacts() {
        return selectedContacts;
    }

    /**
     * @param
     * selectedContacts
     * the
     * selectedContacts
     * to
     * set
     */
    public void setSelectedContacts(List<Contact> selectedContacts) {
        this.selectedContacts = selectedContacts;
    }
}
