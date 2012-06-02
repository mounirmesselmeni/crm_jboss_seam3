package com.insat.gl5.crm_pfa.web.converter.account;

import com.insat.gl5.crm_pfa.model.Account;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@FacesConverter(forClass = Account.class)
public class AccountConverter implements Serializable, Converter {
    private static final long serialVersionUID = 1L;

    
    @Inject
    private List<Account> lstAccounts;

    @Override
    public Object getAsObject(final FacesContext arg0, final UIComponent arg1, final String id) {
            if (id == null) {
            return null;
        }
        for (Account account : lstAccounts) {
            if (account.getName().equals(id)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public String getAsString(final FacesContext context, final UIComponent comp, final Object object) {
        if (object == null) {
            return null;
        }
        return ((Account) object).getName();
    }
}
