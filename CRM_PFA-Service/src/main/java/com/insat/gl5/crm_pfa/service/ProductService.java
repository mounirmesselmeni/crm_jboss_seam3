/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service;

import com.insat.gl5.crm_pfa.model.Category;
import com.insat.gl5.crm_pfa.model.Product;
import com.insat.gl5.crm_pfa.model.TVA;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
public class ProductService extends GenericService {

    public void createProduct(Product product) throws Exception {
        try {
            this.persist(product);
        } catch (Exception ex) {
            this.log.error(ex.getMessage());
            throw ex;
        }
    }

    public void editProduct(Product product) throws Exception {
        try {
            this.edit(product);
        } catch (Exception ex) {
            this.log.error(ex.getMessage());
            throw ex;
        }
    }

    public void deleteProduct(Product product) throws Exception {
        try {
            this.delete(product);
        } catch (Exception ex) {
            this.log.error(ex.getMessage());
            throw ex;
        }
    }

    public void createCategory(Category category) throws Exception {
        try {
            this.persist(category);
        } catch (Exception ex) {
            this.log.error(ex.getMessage());
            throw ex;
        }
    }

    public void editCategory(Category category) throws Exception {
        try {
            this.edit(category);
        } catch (Exception ex) {
            this.log.error(ex.getMessage());
            throw ex;
        }
    }

    public void deleteCategory(Category category) throws Exception {
        try {
            this.delete(category);
        } catch (Exception ex) {
            this.log.error(ex.getMessage());
            throw ex;
        }
    }

    public void createTva(TVA tva) throws Exception {
        try {
            this.persist(tva);
        } catch (Exception ex) {
            this.log.error(ex.getMessage());
            throw ex;
        }
    }

    public void editTva(TVA tva) throws Exception {
        try {
            this.edit(tva);
        } catch (Exception ex) {
            this.log.error(ex.getMessage());
            throw ex;
        }
    }

    public void deleteTva(TVA tva) throws Exception {
        try {
            this.delete(tva);
        } catch (Exception ex) {
            this.log.error(ex.getMessage());
            throw ex;
        }
    }
}
