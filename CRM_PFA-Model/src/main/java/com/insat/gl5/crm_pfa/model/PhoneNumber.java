package com.insat.gl5.crm_pfa.model;

import com.insat.gl5.crm_pfa.enumeration.PhoneNumberType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Entity
public class PhoneNumber extends BaseEntity {

    @NotNull
    @NotEmpty
    private String number;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PhoneNumberType type;
    private boolean verified;

    public PhoneNumber() {
    }

    public PhoneNumber(PhoneNumberType type) {
        this.type = type;
    }

    
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneNumberType getType() {
        return type;
    }

    public void setType(PhoneNumberType type) {
        this.type = type;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

}
