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

    @NotNull
    @Enumerated(EnumType.STRING)
    private AddressType type;
    @NotNull
    private String street;
    @NotNull
    private String city;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Gouvernment gouvernment;
    private String postalCode;

    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String address1) {
        this.street = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

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
        return type + " : " + street + " - " + postalCode + " - " + city + ", " + gouvernment;
    }
}
