package com.insat.gl5.crm_pfa.model.security;

import static org.jboss.seam.security.annotations.management.EntityType.IDENTITY_ATTRIBUTE;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.jboss.seam.security.annotations.management.IdentityEntity;
import org.jboss.seam.security.annotations.management.IdentityProperty;
import org.jboss.seam.security.annotations.management.PropertyType;

/**
 * Stores user attributes
 *
 * @author Shane Bryzak
 */
@IdentityEntity(IDENTITY_ATTRIBUTE)
@Entity
@Table(name="IdentityObjectAttribute", uniqueConstraints=@UniqueConstraint(columnNames={"name", "IDENTITY_OBJECT_ID"}))
public class IdentityObjectAttribute implements Serializable {
    private static final long serialVersionUID = 878658872199149253L;

    private Integer attributeId;
    private IdentityObject identityObject;
    private String name;
    private String value;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer id) {
        this.attributeId = id;
    }

    @ManyToOne
    @JoinColumn(name = "IDENTITY_OBJECT_ID")
    public IdentityObject getIdentityObject() {
        return identityObject;
    }

    public void setIdentityObject(IdentityObject identityObject) {
        this.identityObject = identityObject;
    }

    @IdentityProperty(PropertyType.NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @IdentityProperty(PropertyType.VALUE)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
