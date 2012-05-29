/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.product;

import com.insat.gl5.crm_pfa.model.Product;
import com.insat.gl5.crm_pfa.service.ProductService;
import com.insat.gl5.crm_pfa.web.controller.ConversationController;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.inject.Inject;
import org.jboss.seam.international.status.Messages;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
@Named
@ConversationScoped
public class ProductController extends ConversationController {

    @Inject
    private Product product;
    private Product selectedProduct;
    @Inject
    private ProductService productService;
    @Inject
    private Messages messages;

    /**
     * Creation d'un nouveau produit
     */
    public void createProduct() {
        try {
            this.productService.createProduct(getProduct());
            messages.info("Produit ajouté.");
            endConversation();
        } catch (Exception ex) {
            messages.error("Erreur d'ajout du produit.");
        }
    }

    /**
     * Edition produit
     */
    public void editProduct() {
        try {
            this.productService.editProduct(getSelectedProduct());
            messages.info("Produit à jour.");
        } catch (Exception ex) {
            messages.error("Erreur lors de la modification du produit.");
        }
    }

    /**
     * Suppression produit
     */
    public void deleteProduct() {
        try {
            this.productService.deleteProduct(getSelectedProduct());
            messages.info("Produit supprimé.");
        } catch (Exception ex) {
            messages.error("Erreur de suppression du produit.");
        }
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * @return the selectedProduct
     */
    public Product getSelectedProduct() {
        return selectedProduct;
    }

    /**
     * @param selectedProduct the selectedProduct to set
     */
    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
}
