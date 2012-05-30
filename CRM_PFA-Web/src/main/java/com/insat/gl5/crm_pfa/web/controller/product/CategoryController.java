/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.product;

import com.insat.gl5.crm_pfa.model.Category;
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
public class CategoryController extends ConversationController {

    @Inject
    private Category category;
    private Category selectedCategory;
    @Inject
    private ProductService productService;
    @Inject
    private Messages messages;

    public String create() {
        try {
            productService.createCategory(category);
            messages.info("Catégorie ajoutée.");
            category = new Category();
            return "newCategory.hide();";
        } catch (Exception ex) {
            messages.error("Erreur lors de l'ajout de la catégorie.");
        }
        return null;
    }

    public String edit() {
        try {
            productService.editCategory(selectedCategory);
            messages.info("Catégorie modifiée.");
            endConversation();
            return "editCategory.hide();";
        } catch (Exception ex) {
            messages.error("Erreur lors de la modification de la catégorie.");
        }
        return null;
    }

    public void delete() {
        try {
            productService.deleteCategory(selectedCategory);
            messages.info("Catégorie supprimée.");
            endConversation();
        } catch (Exception ex) {
            messages.error("Erreur lors de suppression de la catégorie.");
        }
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the selectedCategory
     */
    public Category getSelectedCategory() {
        return selectedCategory;
    }

    /**
     * @param selectedCategory the selectedCategory to set
     */
    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
    }
}
