package com.insat.gl5.crm_pfa.web.converter.account;

import com.insat.gl5.crm_pfa.model.Account;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@RequestScoped
@FacesConverter(forClass = Account.class)
public class AccountConverter implements Serializable, Converter {
    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    @Override
    public Object getAsObject(final FacesContext arg0, final UIComponent arg1, final String id) {
        if (!id.isEmpty()) {
            Query query = em.createNamedQuery("Account.findByName");
            query.setParameter(1, id);
            try{ 
             return  query.getSingleResult();
            }catch(NoResultException ex){
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(final FacesContext context, final UIComponent comp, final Object object) {
        if (object != null) {
            Account account = (Account) object;
            return account.getName();
        } else {
            return null;
        }
    }
}
