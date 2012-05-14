package com.insat.gl5.crm_pfa.model;

import com.insat.gl5.crm_pfa.enumeration.AddressType;
import com.insat.gl5.crm_pfa.enumeration.Gouvernment;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Entity
public class Address extends BaseEntity {

    
    private String description;
    @Enumerated(EnumType.STRING)
    private AddressType type;
    private String street;
    private String city;
    @Enumerated(EnumType.STRING)
    private Gouvernment gouvernment;
    private String postalCode;

        public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
        this.type = type;
    }

    @NotNull
    public String getStreet() {
        return street;
    }

    public void setStreet(String address1) {
        this.street = address1;
    }

    @NotNull
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @NotNull
    public Gouvernment getGouvernment() {
        return gouvernment;
    }

    public void setGouvernment(Gouvernment gouvernment) {
        this.gouvernment = gouvernment;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return type + ": " + street + " -" + postalCode + "-" + city + ", " + gouvernment ;
    }
}
