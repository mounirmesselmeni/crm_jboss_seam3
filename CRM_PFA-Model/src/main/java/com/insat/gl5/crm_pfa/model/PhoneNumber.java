package com.insat.gl5.crm_pfa.model;

import com.insat.gl5.crm_pfa.enumeration.PhoneNumberType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Entity
public class PhoneNumber extends BaseEntity {

    private String number;
    private PhoneNumberType type;
    private boolean verified;
    private Contact contact;

    @NotNull
    @NotEmpty
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @NotNull
    @Enumerated(EnumType.STRING)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact", nullable = true)
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
