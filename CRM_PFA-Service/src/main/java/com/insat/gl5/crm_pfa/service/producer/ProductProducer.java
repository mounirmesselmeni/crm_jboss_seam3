/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service.producer;

import com.insat.gl5.crm_pfa.model.Product;
import com.insat.gl5.crm_pfa.service.qualifier.DataRepository;
import java.util.List;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
public class ProductProducer {

    @Inject
    @DataRepository
    private EntityManager em;

    /**
     * Products list
     * @return 
     */
    @Produces
    @Named("lstProducts")
    public List<Product> getLstProducts() {
        Query query = em.createQuery("select c from Product c");
        return query.getResultList();
    }
}
