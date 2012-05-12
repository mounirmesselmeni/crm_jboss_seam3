/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import com.insat.gl5.crm_pfa.enumeration.CustomerType;
import javax.persistence.Entity;

/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Entity
public class Account extends BaseEntity {

    private String name;
    private CustomerType type;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public CustomerType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(CustomerType type) {
        this.type = type;
    }
}
