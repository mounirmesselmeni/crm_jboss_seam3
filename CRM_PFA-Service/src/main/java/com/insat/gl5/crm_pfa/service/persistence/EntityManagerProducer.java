/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service.persistence;

import com.insat.gl5.crm_pfa.service.qualifier.DataRepository;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import org.jboss.solder.core.ExtensionManaged;

/**
 *
 * @author Mounir Messelmeni, contact: messelmeni.mounir@gmail.com
 */
@Singleton
public class EntityManagerProducer {

    @Produces
    @ExtensionManaged
    @ConversationScoped
    @PersistenceUnit(unitName = "unit")
    private EntityManagerFactory emf;
    @Produces
    @DataRepository
    @PersistenceContext(unitName = "unit")
    private EntityManager em;
}
