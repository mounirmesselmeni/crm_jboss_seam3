/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service.producer;

import com.insat.gl5.crm_pfa.model.TVA;
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
public class TvaProducer {

    @Inject
    @DataRepository
    private EntityManager em;

    /**
     * Tva list
     * @return 
     */
    @Produces
    @Named("lstTva")
    public List<TVA> getLstTva() {
        Query query = em.createQuery("select c from TVA c");
        return query.getResultList();
    }
}
