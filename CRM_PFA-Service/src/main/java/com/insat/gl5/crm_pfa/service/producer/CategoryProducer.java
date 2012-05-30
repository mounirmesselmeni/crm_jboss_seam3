/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service.producer;

import com.insat.gl5.crm_pfa.model.Category;
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
public class CategoryProducer {
    @Inject
    @DataRepository
    private EntityManager em;

    /**
     * Categories list
     * @return 
     */
    @Produces
    @Named("lstCategories")
    public List<Category> getLstCategories() {
        Query query = em.createQuery("select c from Category c");
        return query.getResultList();
    }
}
