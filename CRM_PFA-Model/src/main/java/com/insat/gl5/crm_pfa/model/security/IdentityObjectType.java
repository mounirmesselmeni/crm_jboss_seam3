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
 * A lookup table containing identity object types
 *
 * @author Shane Bryzak
 */
@Entity
@Table(name="IdentityObjectType")
public class IdentityObjectType implements Serializable {
    private static final long serialVersionUID = -8333008038699510742L;

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
