/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.converter.products;

import com.insat.gl5.crm_pfa.model.TVA;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
@FacesConverter(forClass = TVA.class)
@RequestScoped
public class TvaConverter implements Converter {

    @Inject
    private List<TVA> lstTva;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null) {
            return null;
        }
        Float floatValue = Float.valueOf(string);
        for (TVA tva : lstTva) {
            if (Float.compare(tva.getTaux(), floatValue) == 0) {
                return tva;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object == null) {
            return null;
        }
        return ((TVA) object).getTaux().toString();
    }
}
