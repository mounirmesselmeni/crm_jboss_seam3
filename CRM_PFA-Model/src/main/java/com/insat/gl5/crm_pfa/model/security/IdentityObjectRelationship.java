package com.insat.gl5.crm_pfa.model.security;

import static org.jboss.seam.security.annotations.management.EntityType.IDENTITY_RELATIONSHIP;

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
 * Contains relationships between identities
 *
 * @author Shane Bryzak
 */
@IdentityEntity(IDENTITY_RELATIONSHIP)
@Entity
@Table(name="IdentityObjectRelationship", uniqueConstraints=@UniqueConstraint(columnNames={"FROM_IDENTITY_ID", "TO_IDENTITY_ID", "RELATIONSHIP_TYPE_ID", "name"}))
public class IdentityObjectRelationship implements Serializable {
    private static final long serialVersionUID = -5254503795105571898L;

    private Long id;
    private String name;
    private IdentityObjectRelationshipType relationshipType;
    private IdentityObject from;
    private IdentityObject to;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @IdentityProperty(PropertyType.NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @IdentityProperty(PropertyType.TYPE)
    @JoinColumn(name = "RELATIONSHIP_TYPE_ID")
    public IdentityObjectRelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(IdentityObjectRelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    @ManyToOne
    @IdentityProperty(PropertyType.RELATIONSHIP_FROM)
    @JoinColumn(name = "FROM_IDENTITY_ID")
    public IdentityObject getFrom() {
        return from;
    }

    public void setFrom(IdentityObject from) {
        this.from = from;
    }

    @ManyToOne
    @IdentityProperty(PropertyType.RELATIONSHIP_TO)
    @JoinColumn(name = "TO_IDENTITY_ID")
    public IdentityObject getTo() {
        return to;
    }

    public void setTo(IdentityObject to) {
        this.to = to;
    }
}
