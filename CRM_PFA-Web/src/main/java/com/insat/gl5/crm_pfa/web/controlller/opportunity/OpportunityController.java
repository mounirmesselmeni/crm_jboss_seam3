/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controlller.opportunity;

import com.insat.gl5.crm_pfa.model.*;
import com.insat.gl5.crm_pfa.service.ItemToPurchaseService;
import com.insat.gl5.crm_pfa.service.OpportunityService;
import com.insat.gl5.crm_pfa.service.ProductService;
import com.insat.gl5.crm_pfa.web.controller.ConversationController;
import com.insat.gl5.crm_pfa.web.viewModel.EmailViewModel;
import com.insat.gl5.crm_pfa.web.viewModel.PhoneNumberViewModel;
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
    private ItemToPurchaseService  itemToPurchaseService;
    @Inject
    private ProductService productService;
    private List<Product> products = new LinkedList<Product>();
    @Inject
    private Opportunity opportunity;
    @Inject
    private Messages messages;
    private String redirect;
    //Liste des produits séléctionnés à partir du datatable
    private List<ItemToPurchase> itemsToPurchase = new LinkedList<ItemToPurchase>();
    private List<ItemToPurchase> lstItemsToDelete = new LinkedList<ItemToPurchase>();
    private List<ItemToPurchase> lstItemsToAdd = new LinkedList<ItemToPurchase>();
    private Product selctedProduct;
    private int quantity;
    private Category filterCategory;
    private String filterProductName;

    /**
     * Save
     * an
     * opportunity
     *
     */
    public String saveOpportunity() {

        try {

            affectProducts();
            opportunityService.saveOpportunity(getOpportunity());

            messages.info("Opportunité {0} est enregistrée avec succés !", getOpportunity());
            setOpportunity(null);

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
            editProducts();
            opportunityService.editOpportunity(getOpportunity());
            messages.info("Opportunité {0} est modifiée avec succés !", getOpportunity());

            setOpportunity(null);

        } catch (Exception e) {
            messages.error("Erreur de modification de l'Opportunité {0}", getOpportunity());
            return null;
        }
        endConversation();
        return getRedirect();
    }

    private void editProducts() {
        for (ItemToPurchase item : lstItemsToDelete) {
            try {
                itemToPurchaseService.deleteItemToPurchase(item);
                opportunity.getItemsToPurchase().remove(item);
            } catch (Exception ex) {
                messages.error("Erreur de supression de l'item {0}", item);
            }
        }
        for (ItemToPurchase item : lstItemsToAdd) {
            try {
                itemToPurchaseService.saveItemToPurchase(item);
                opportunity.getItemsToPurchase().add(item);
            } catch (Exception ex) {
                messages.error("Erreur de l'ajout de l'item {0}", item);
            }
        }
    }

    public String getOpportunityPrice(Product product) {
        return String.valueOf(product.getPrice() * ((100 - opportunity.getRelatedTo().getFidelity().getScore()) / 100));
    }

    public void initProducts(){
        beginConversation();
        products = productService.getAllProducts();
    }
    public void loadProducts(){
        beginConversation();
        products = productService.getAllProducts();
        itemsToPurchase = opportunity.getItemsToPurchase();
        for(ItemToPurchase item : itemsToPurchase){
            products.remove(item.getProduct());
        }
    }
    /**
     * Delete
     * the
     * selected
     * account
     */
    public void deleteOpportunity() {

        try {
            opportunityService.deleteOpportunity(getOpportunity());
            messages.info("Opportunité {0} est supprimée avec succés !", getOpportunity());

            setOpportunity(null);

        } catch (Exception e) {
            messages.error("Erreur de supression de l'Opportunité {0}", getOpportunity());
        }
        endConversation();
    }

    public void addItem() {
        itemsToPurchase.add(new ItemToPurchase(quantity, selctedProduct));
        products.remove(selctedProduct);
    }

    public void removeItem(ItemToPurchase itemToPurchase){
        itemsToPurchase.remove(itemToPurchase);
        products.add(itemToPurchase.getProduct());
    }
    private void affectProducts() {
        opportunity.setItemsToPurchase(itemsToPurchase);
    }

    public void populateProducts() {
        products.clear();
        if (filterCategory == null) {
            if (filterProductName == null || filterProductName.isEmpty()) {
                //-------Criteres : none--------//
                products = productService.getAllProducts();
                return;
            }
                //-------Criteres : ProductName--------//
            products = productService.findProductsByLikeName(filterProductName);
            return;
        }
         if (filterProductName == null || filterProductName.isEmpty()) {
                //-------Criteres : Category--------//
                products = productService.findProductsByCategory(filterCategory);
                return;
            }
                //-------Criteres : Category+productName--------//
            products = productService.findProductsByLikeNameAndCategory(filterProductName, filterCategory);
    }

    public List<String> complete(String query) {

        List<String> filtredProductNames = productService.getFiltredProductNames(query);
        return filtredProductNames;
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
     * itemsToPurchase
     */
    public List<ItemToPurchase> getItemsToPurchase() {
        return itemsToPurchase;
    }

    /**
     * @param
     * itemsToPurchase
     * the
     * itemsToPurchase
     * to
     * set
     */
    public void setItemsToPurchase(List<ItemToPurchase> itemsToPurchase) {
        this.itemsToPurchase = itemsToPurchase;
    }

    /**
     * @return
     * the
     * selctedProduct
     */
    public Product getSelctedProduct() {
        return selctedProduct;
    }

    /**
     * @param
     * selctedProduct
     * the
     * selctedProduct
     * to
     * set
     */
    public void setSelctedProduct(Product selctedProduct) {
        this.selctedProduct = selctedProduct;
    }

    /**
     * @return
     * the
     * quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param
     * quantity
     * the
     * quantity
     * to
     * set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return
     * the
     * products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * @param
     * products
     * the
     * products
     * to
     * set
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * @return
     * the
     * filterCategory
     */
    public Category getFilterCategory() {
        return filterCategory;
    }

    /**
     * @param
     * filterCategory
     * the
     * filterCategory
     * to
     * set
     */
    public void setFilterCategory(Category filterCategory) {
        this.filterCategory = filterCategory;
    }

    /**
     * @return
     * the
     * filterProductName
     */
    public String getFilterProductName() {
        return filterProductName;
    }

    /**
     * @param
     * filterProductName
     * the
     * filterProductName
     * to
     * set
     */
    public void setFilterProductName(String filterProductName) {
        this.filterProductName = filterProductName;
    }
}
