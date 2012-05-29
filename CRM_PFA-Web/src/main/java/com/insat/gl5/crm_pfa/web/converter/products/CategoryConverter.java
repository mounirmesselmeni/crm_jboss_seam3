/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.converter.products;

import com.insat.gl5.crm_pfa.model.Category;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
@FacesConverter(forClass = Category.class)
public class CategoryConverter implements Converter {

    @Inject
    private List<Category> lstCategories;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null) {
            return null;
        }
        for (Category category : lstCategories) {
            if (category.getName().equals(string)) {
                return category;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if (object == null) {
            return null;
        }
        return ((Category) object).getName();
    }
}
