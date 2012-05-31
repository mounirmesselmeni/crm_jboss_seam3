/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service;

import com.insat.gl5.crm_pfa.model.Account;
import com.insat.gl5.crm_pfa.model.Category;
import com.insat.gl5.crm_pfa.model.Product;
import com.insat.gl5.crm_pfa.model.TVA;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
     /**
     * Get a list of all products figured in database
     *
     * @return 
     */
    public List<Product> getAllProducts() {
        TypedQuery query = em.createQuery("SELECT p FROM Product p", Product.class);
        return query.getResultList();
    }
    
     public List<String> getFiltredProductNames(String name){
        Query query = em.createQuery("SELECT p.name FROM Product p WHERE p.name LIKE '%"+name+"'");
        return query.getResultList();
    }
     
    public List<Product> findProductsByName(String name) {
        TypedQuery query = em.createQuery("SELECT p FROM Product p WHERE p.name =?1", Product.class).setParameter(1, name);
        return query.getResultList();
    }
    
    public List<Product> findProductsByLikeName(String name) {
        TypedQuery query = em.createQuery("SELECT p FROM Product p WHERE p.name LIKE '"+name+"%'", Product.class);
        return query.getResultList();
    }
    public List<Product> findProductsByCategory(Category category) {
        TypedQuery query = em.createQuery("SELECT p FROM Product p WHERE p.category =?1", Product.class).setParameter(1, category);
        return query.getResultList();
    }
    public List<Product> findProductsByLikeNameAndCategory(String name,Category category) {
        TypedQuery query = em.createQuery("SELECT p FROM Product p WHERE  p.name LIKE '"+name+"%' AND p.category =?1", Product.class).setParameter(1, category);
        return query.getResultList();
    }
    public List<Product> findProductsByNameAndCategory(String name,Category category) {
        TypedQuery query = em.createQuery("SELECT p FROM Product p WHERE  p.name = ?1 AND p.category =?2", Product.class).setParameter(1, name).setParameter(2, category);
        return query.getResultList();
    }
}
