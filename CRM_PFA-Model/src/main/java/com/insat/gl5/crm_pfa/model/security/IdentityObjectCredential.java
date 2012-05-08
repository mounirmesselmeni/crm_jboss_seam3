package com.insat.gl5.crm_pfa.model.security;

import static org.jboss.seam.security.annotations.management.EntityType.IDENTITY_CREDENTIAL;

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
 * Holds credential values
 *
 * @author Shane Bryzak
 */
@IdentityEntity(IDENTITY_CREDENTIAL)
@Entity
@Table(name="IdentityObjectCredential", uniqueConstraints=@UniqueConstraint(columnNames={"IDENTITY_OBJECT_ID", "CREDENTIAL_TYPE_ID"}))
public class IdentityObjectCredential implements Serializable {
    private static final long serialVersionUID = 1359292319831314803L;

    private Long id;
    private IdentityObject identityObject;
    private IdentityObjectCredentialType type;
    private String value;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "IDENTITY_OBJECT_ID")
    public IdentityObject getIdentityObject() {
        return identityObject;
    }

    public void setIdentityObject(IdentityObject identityObject) {
        this.identityObject = identityObject;
    }

    @ManyToOne
    @IdentityProperty(PropertyType.TYPE)
    @JoinColumn(name = "CREDENTIAL_TYPE_ID")
    public IdentityObjectCredentialType getType() {
        return type;
    }

    public void setType(IdentityObjectCredentialType type) {
        this.type = type;
    }

    @IdentityProperty(PropertyType.VALUE)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
