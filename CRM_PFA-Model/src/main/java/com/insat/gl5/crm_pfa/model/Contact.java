package com.insat.gl5.crm_pfa.model;

import com.insat.gl5.crm_pfa.enumeration.Salutation;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Entity
public class Contact extends CrmUser {

    private String firstName;
    private String lastName;
    private String imageURL;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PhoneNumber> lstPhoneNumbers = new LinkedList<PhoneNumber>();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> lstAddresses = new LinkedList<Address>();
    @Enumerated(EnumType.STRING)
    private Salutation salutation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

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

    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * @return the lstPhoneNumbers
     */
    public List<PhoneNumber> getLstPhoneNumbers() {
        return lstPhoneNumbers;
    }

    /**
     * @param lstPhoneNumbers the lstPhoneNumbers to set
     */
    public void setLstPhoneNumbers(List<PhoneNumber> lstPhoneNumbers) {
        this.lstPhoneNumbers = lstPhoneNumbers;
    }

    /**
     * @return the lstAddresses
     */
    public List<Address> getLstAddresses() {
        return lstAddresses;
    }

    /**
     * @param lstAddresses the lstAddresses to set
     */
    public void setLstAddresses(List<Address> lstAddresses) {
        this.lstAddresses = lstAddresses;
    }

    /**
     * @return the imageURL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * @param imageURL the imageURL to set
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return salutation + ". " + firstName + " " + lastName;
    }
}
