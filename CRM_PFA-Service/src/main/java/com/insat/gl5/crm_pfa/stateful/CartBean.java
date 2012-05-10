/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.stateful;

import com.insat.gl5.crm_pfa.model.Devis;
import com.insat.gl5.crm_pfa.model.LineItem;
import com.insat.gl5.crm_pfa.model.Product;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.ejb.Stateful;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Stateful
public class CartBean{

   
    private Devis devis;
    private Collection<LineItem> lineItemList;

    public CartBean() {
        this.lineItemList = new ArrayList<LineItem>();
    }

    public void addLineItem(Product product, int quantity) {
        LineItem lineItem = getLineItemByProduct(product);
        double price = product.getPrice();
        if (lineItem == null) {
            lineItem = getDevis().addPurchaseAndLineItem(product, quantity, price);
            if (!lineItemList.contains(lineItem)) {
                lineItemList.add(lineItem);
            }
        } else {
            getDevis().addPurchase(lineItem, quantity, price);
        }
    }

    public void updateLineItem(LineItem lineItem, int quantity) {
        double price = lineItem.getProduct().getPrice();
        getDevis().updatePurchaseAndUpdateLineItem(lineItem, quantity, price);
    }

    public void deleteLineItem(Long lineItemId) {
        Collection<LineItem> lineItemListTest = new ArrayList<LineItem>();
        for (Iterator<LineItem> it = lineItemList.iterator(); it.hasNext();) {
            LineItem lineItem = it.next();
            if (lineItem.getId().equals(lineItemId)) {
                lineItemListTest.add(lineItem);
            }
        }
        lineItemList.removeAll(lineItemListTest);
    }

    public Collection<LineItem> getLineItemList() {
        return lineItemList;
    }

    public double getTotalPriceLineItemList() {
        double totlalPrice = 0;
        for (Iterator<LineItem> it = lineItemList.iterator(); it.hasNext();) {
            LineItem lineItem = it.next();
            totlalPrice = totlalPrice + lineItem.getSubTotal();
        }
        return totlalPrice;
    }

    public void setLineItemList(Collection<LineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }

    public LineItem getLineItemByProduct(Product product) {
        LineItem lineItem = null;
        if ((lineItemList != null) && (!lineItemList.isEmpty())) {
            for (Iterator<LineItem> it = lineItemList.iterator(); it.hasNext();) {
                LineItem item = it.next();
                if (item.getProduct().equals(product)) {
                    lineItem = item;
                }
            }
        }
        return lineItem;
    }

    public Devis getDevis() {
        if (devis == null) {
            devis = new Devis();
        }
        return devis;
    }
}
