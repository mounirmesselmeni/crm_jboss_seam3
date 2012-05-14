package com.insat.gl5.crm_pfa.model;

import com.insat.gl5.crm_pfa.enumeration.Salutation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Entity
public class Contact extends BaseEntity {

    @Email
    @Column(unique = true)
    private String emailAddress;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Salutation salutation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "primaryAddressId")
    private Address primaryAddress;
    @ManyToOne
    @JoinColumn(name = "secondaryAddressId")
    private Address secondaryAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @NotNull
    @NotEmpty
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @NotEmpty
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

        public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * @return the salutation
     */
        public Salutation getSalutation() {
        return salutation;
    }

    /**
     * @param salutation the salutation to set
     */
    public void setSalutation(Salutation salutation) {
        this.salutation = salutation;
    }

    /**
     * @return the primaryAddress
     */
    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    /**
     * @param primaryAddress the primaryAddress to set
     */
    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    /**
     * @return the secondaryAddress
     */
    public Address getSecondaryAddress() {
        return secondaryAddress;
    }

    /**
     * @param secondaryAddress the secondaryAddress to set
     */
    public void setSecondaryAddress(Address secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }
    
    public String getFullName(){
        return firstName+" "+lastName;
    }
}
