/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.model;

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
    protected Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;

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

    @PrePersist
    public void initTimeStamps() {
        // we do this for the purpose of the demo, this lets us create our own
        // creation dates. Typically we would just set the createdOn field.
        if (createdOn == null) {
            createdOn = new Date();
        }
        modifiedOn = createdOn;
    }

    @PreUpdate
    public void updateTimeStamp() {
        modifiedOn = new Date();
    }

    public boolean isPersistent() {
        return getId() != null;
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
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 37 * hash + (this.createdOn != null ? this.createdOn.hashCode() : 0);
        hash = 37 * hash + (this.modifiedOn != null ? this.modifiedOn.hashCode() : 0);
        return hash;
    }
}