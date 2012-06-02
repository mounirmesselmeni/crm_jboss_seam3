/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controlller.opportunity;

import com.insat.gl5.crm_pfa.model.*;
import com.insat.gl5.crm_pfa.service.ItemToPurchaseService;
import com.insat.gl5.crm_pfa.service.ProductService;
import com.insat.gl5.crm_pfa.web.controller.ConversationController;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
public class ItemsToPurchaseController extends ConversationController {

    @Inject
    private ItemToPurchaseService itemToPurchaseService;
    @Inject
    private ProductService productService;
    private List<Product> products = new LinkedList<Product>();
    //Liste des produits séléctionnés à partir du datatable
    private List<ItemToPurchase> itemsToPurchase = new LinkedList<ItemToPurchase>();
    private List<ItemToPurchase> lstItemsToDelete = new LinkedList<ItemToPurchase>();
    private List<ItemToPurchase> lstItemsToAdd = new LinkedList<ItemToPurchase>();
    private Product selctedProduct;
    private int quantity;
    private Category filterCategory;
    private String filterProductName;

    public void deleteItem(ItemToPurchase item) throws Exception {
        itemToPurchaseService.deleteItemToPurchase(item);
    }

    public void saveItem(ItemToPurchase item) throws Exception {
        itemToPurchaseService.saveItemToPurchase(item);
    }

    public void initProducts() {
        products = productService.getAllProducts();
    }

    public void loadProducts(List<ItemToPurchase> items) {
        products = productService.getAllProducts();
        itemsToPurchase = items;
//        for (ItemToPurchase item : itemsToPurchase) {
//            products.remove(item.getProduct());
//        }
    }

    public void addItem() {
        ItemToPurchase item = getItemToPurchaseByProduct(selctedProduct);

        if (item == null) {
            itemsToPurchase.add(new ItemToPurchase(quantity, selctedProduct));
        }else{
            item.setQuantity(item.getQuantity()+quantity);
        }
        quantity =0;
//        products.remove(selctedProduct);
    }

    public void removeItem(ItemToPurchase itemToPurchase) {
        itemsToPurchase.remove(itemToPurchase);
//        products.add(itemToPurchase.getProduct());
    }

    private ItemToPurchase getItemToPurchaseByProduct(Product product) {
        for (ItemToPurchase item : itemsToPurchase) {
            if (item.getProduct().equals(product)) {
                return item;
            }
        }
        return null;
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

    /**
     * @return
     * the
     * lstItemsToDelete
     */
    public List<ItemToPurchase> getLstItemsToDelete() {
        return lstItemsToDelete;
    }

    /**
     * @param
     * lstItemsToDelete
     * the
     * lstItemsToDelete
     * to
     * set
     */
    public void setLstItemsToDelete(List<ItemToPurchase> lstItemsToDelete) {
        this.lstItemsToDelete = lstItemsToDelete;
    }

    /**
     * @return
     * the
     * lstItemsToAdd
     */
    public List<ItemToPurchase> getLstItemsToAdd() {
        return lstItemsToAdd;
    }

    /**
     * @param
     * lstItemsToAdd
     * the
     * lstItemsToAdd
     * to
     * set
     */
    public void setLstItemsToAdd(List<ItemToPurchase> lstItemsToAdd) {
        this.lstItemsToAdd = lstItemsToAdd;
    }
}
