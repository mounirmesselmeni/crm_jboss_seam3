/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

import com.insat.gl5.crm_pfa.model.security.UserDetails;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    @ManyToOne
    private UserDetails userCreation;
    @ManyToOne
    private UserDetails userModification;

    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return new Date(createdOn.getTime());
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = new Date(createdOn.getTime());
    }

    public Date getModifiedOn() {
        return new Date(modifiedOn.getTime());
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = new Date(modifiedOn.getTime());
    }

    public UserDetails getUserCreation() {
        return userCreation;
    }

    public void setUserCreation(UserDetails userCreation) {
        this.userCreation = userCreation;
    }

    public UserDetails getUserModification() {
        return userModification;
    }

    public void setUserModification(UserDetails userModification) {
        this.userModification = userModification;
    }

    
    @PrePersist
    public void initTimeStamps() {
        // we do this for the purpose of the demo, this lets us create our own
        // creation dates. Typically we would just set the createdOn field.
        if (createdOn == null) {
            createdOn = new Date();
        //    userModification = userDetails;
           
        }
        modifiedOn = createdOn;
        //userModification = userDetails;
    }

    
    @PreUpdate
    public void updateTimeStamp() {
        modifiedOn = new Date();
     //   userModification = userDetails;
       
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseEntity other = (BaseEntity) obj;
        if (!(this.id == null || this.id.equals(other.id)) && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.createdOn != other.createdOn && (this.createdOn == null || !this.createdOn.equals(other.createdOn))) {
            return false;
        }
        if (this.modifiedOn != other.modifiedOn && (this.modifiedOn == null || !this.modifiedOn.equals(other.modifiedOn))) {
            return false;
        }
        if (this.userCreation != other.userCreation && (this.userCreation == null || !this.userCreation.equals(other.userCreation))) {
            return false;
        }
        if (this.userModification != other.userModification && (this.userModification == null || !this.userModification.equals(other.userModification))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 37 * hash + (this.createdOn != null ? this.createdOn.hashCode() : 0);
        hash = 37 * hash + (this.modifiedOn != null ? this.modifiedOn.hashCode() : 0);
        hash = 37 * hash + (this.userCreation != null ? this.userCreation.hashCode() : 0);
        hash = 37 * hash + (this.userModification != null ? this.userModification.hashCode() : 0);
        return hash;
    }
}