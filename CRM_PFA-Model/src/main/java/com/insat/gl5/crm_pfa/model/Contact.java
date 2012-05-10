package com.insat.gl5.crm_pfa.model;

import com.insat.gl5.crm_pfa.enumeration.Salutation;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Entity
public class Contact extends BaseEntity {

    private String emailAddress;
    private String firstName;
    private String lastName;
    private String roleName;
    private Salutation salutation;
    private Account account;
    private List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();

    @Email
    @Column(unique = true)
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account", nullable = false)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY)
    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    /**
     * @return the salutation
     */
    @Enumerated(EnumType.STRING)
    public Salutation getSalutation() {
        return salutation;
    }

    /**
     * @param salutation the salutation to set
     */
    public void setSalutation(Salutation salutation) {
        this.salutation = salutation;
    }
}
