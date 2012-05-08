package com.insat.gl5.crm_pfa.model.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jboss.seam.security.annotations.management.IdentityProperty;
import org.jboss.seam.security.annotations.management.PropertyType;

/**
 * Lookup table containing relationship types
 *
 * @author Shane Bryzak
 */
@Entity
@Table(name="IdentityObjectRelationshipType")
public class IdentityObjectRelationshipType implements Serializable {
    private static final long serialVersionUID = -67640567413388470L;

    private Long id;
    private String name;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @IdentityProperty(PropertyType.NAME)
    @Column(unique=true) 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
