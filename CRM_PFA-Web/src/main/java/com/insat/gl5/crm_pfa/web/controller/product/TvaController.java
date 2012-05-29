/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.product;

import com.insat.gl5.crm_pfa.model.Category;
import com.insat.gl5.crm_pfa.model.TVA;
import com.insat.gl5.crm_pfa.service.ProductService;
import com.insat.gl5.crm_pfa.web.controller.ConversationController;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.seam.international.status.Messages;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
@Named
@ConversationScoped
public class TvaController extends ConversationController {

    @Inject
    private TVA tva;
    private TVA selectedTva;
    @Inject
    private ProductService productService;
    @Inject
    private Messages messages;

    public String create() {
        try {
            productService.createTva(getTva());
            messages.info("TVA ajoutée.");
            setTva(new TVA());
            return "newTva.hide();";
        } catch (Exception ex) {
            messages.error("Erreur lors de l'ajout de la TVA.");
        }
        return null;
    }

    public String edit() {
        try {
            productService.editTva(getSelectedTva());
            messages.info("TVA modifiée.");
            endConversation();
            return "editCategory.hide();";
        } catch (Exception ex) {
            messages.error("Erreur lors de la modification de la TVA.");
        }
        return null;
    }

    public void delete() {
        try {
            productService.deleteTva(getSelectedTva());
            messages.info("TVA supprimée.");
            endConversation();
        } catch (Exception ex) {
            messages.error("Erreur lors de suppression de la TVA.");
        }
    }

    /**
     * @return the tva
     */
    public TVA getTva() {
        return tva;
    }

    /**
     * @param tva the tva to set
     */
    public void setTva(TVA tva) {
        this.tva = tva;
    }

    /**
     * @return the selectedTva
     */
    public TVA getSelectedTva() {
        return selectedTva;
    }

    /**
     * @param selectedTva the selectedTva to set
     */
    public void setSelectedTva(TVA selectedTva) {
        this.selectedTva = selectedTva;
    }
}
