/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.product;

import com.insat.gl5.crm_pfa.model.Product;
import com.insat.gl5.crm_pfa.service.ProductService;
import com.insat.gl5.crm_pfa.web.controller.ConversationController;
import com.insat.gl5.crm_pfa.web.controller.FileUploadController;
import java.io.File;
import java.io.IOException;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
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
    @Inject
    protected FileUploadController fileUploadController;
    protected final String PRODUCTS_DIRECTORY =
            FacesContext.getCurrentInstance().getExternalContext().getRealPath("/CRMData/products/") + "/";

    /**
     * Creation d'un nouveau produit
     */
    public String createProduct() {
        try {
            try {
                uploadImage();
            } catch (IOException ex) {
                messages.error("Erreur d'upload");
                return null;
            }
            this.productService.createProduct(getProduct());
            messages.info("Produit ajouté.");
            endConversation();
            this.product = new Product();
            return "list";
        } catch (Exception ex) {
            messages.error("Erreur d'ajout du produit.");
        }
        return null;
    }

    private void uploadImage() throws IOException {
        // Création du répertoire du client
        File dir = new File(PRODUCTS_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String urlLogo = dir.getAbsolutePath() + "//" + product.getName() + "_" + product.getReference();
        File file = new File(urlLogo);
        if (file.exists()) {
            file.delete();
        }
        // Sauvegarder l'image du produit
        if (fileUploadController.getFile() != null) {
            String fileName = fileUploadController.getFile().getFileName();
            if (!fileName.isEmpty()) {
                String suffix = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
                if (!suffix.isEmpty()) {
                    fileUploadController.upload(urlLogo + suffix);
                    product.setImage(product.getName() + "_" + product.getReference() + suffix);
                }
            }
        }
        fileUploadController.resetFile();
    }

    /**
     * Edition produit
     */
    public String editProduct() {
        try {
            if (fileUploadController.getFile() != null) {
                try {
                    uploadImage();
                } catch (IOException ex) {
                    messages.error("Erreur d'upload.");
                    return null;
                }
            }
            this.productService.editProduct(product);
            messages.info("Produit à jour.");
            product = new Product();
            return "list";
        } catch (Exception ex) {
            messages.error("Erreur lors de la modification du produit.");
        }
        return null;
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
