/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service;

import com.insat.gl5.crm_pfa.model.BaseEntity;
import com.insat.gl5.crm_pfa.model.Contact;
import com.insat.gl5.crm_pfa.service.qualifier.CurrentUser;
import com.insat.gl5.crm_pfa.service.qualifier.DataRepository;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import org.jboss.solder.logging.Logger;

/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@TransactionAttribute
@Interceptors(org.jboss.seam.transaction.TransactionInterceptor.class)
public abstract class GenericService {

    @Inject
    @DataRepository
    protected EntityManager em;
    
    @Inject
    protected Logger log;

    @Inject
    @CurrentUser
    private Contact currentUser;

    public GenericService() {
        super();
    }

    public GenericService(EntityManager em) {
        this.em = em;
    }

    /**
     * Persister un objet 
     * @param object l'objet à persister
     */
    protected <T extends BaseEntity> void persist(T object) {
        object.setCreatedBy(currentUser);
        em.persist(object);
    }

    /**
     * Supprimer un objet 
     * @param object l'objet à supprimer
     */
    protected void delete(Object object) {
        object = em.merge(object);
        em.remove(object);
    }

    /**
     * Editer un objet 
     * @param object l'objet à éditer
     */
    protected <T extends BaseEntity> void edit(T object) {
        object = em.merge(object);
        object.setModifiedBy(currentUser);
        em.persist(object);
    }

}
