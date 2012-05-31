/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.viewModel;

import com.insat.gl5.crm_pfa.model.ItemToPurchase;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */ 
public class ItemToPurchaseDataModel extends ListDataModel<ItemToPurchase> implements SelectableDataModel<ItemToPurchase> {

    public ItemToPurchaseDataModel() {
    }

    public ItemToPurchaseDataModel(List<ItemToPurchase> list) {
        super(list);
    }
    
    @Override  
    public ItemToPurchase getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<ItemToPurchase> ItemToPurchases = (List<ItemToPurchase>) getWrappedData();  
          
        for(ItemToPurchase ann : ItemToPurchases) {  
            if(ann.getProduct().getName().equals(rowKey))  
                return ann;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(ItemToPurchase ann) {  
        return ann.getProduct().getName();  
    }  
    
    public void addItem(ItemToPurchase item){
         List<ItemToPurchase> ItemToPurchases = (List<ItemToPurchase>) getWrappedData();  
         ItemToPurchases.add(item);
    }
    
    
    public void removeItem(ItemToPurchase item){
         List<ItemToPurchase> ItemToPurchases = (List<ItemToPurchase>) getWrappedData();  
         ItemToPurchases.remove(item);
    }
}  
