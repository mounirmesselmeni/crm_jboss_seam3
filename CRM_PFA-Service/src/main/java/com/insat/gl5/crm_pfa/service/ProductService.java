/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service;

import com.insat.gl5.crm_pfa.model.Product;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
public class ProductService extends GenericService {

    public void create(Product product) throws Exception {
        try {
            this.persist(product);
        } catch (Exception ex) {
            this.log.error(ex.getMessage());
            throw ex;
        }
    }

    public void edit(Product product) throws Exception {
        try {
            this.edit(product);
        } catch (Exception ex) {
            this.log.error(ex.getMessage());
            throw ex;
        }
    }

    public void delete(Product product) throws Exception {
        try {
            this.delete(product);
        } catch (Exception ex) {
            this.log.error(ex.getMessage());
            throw ex;
        }
    }
}
