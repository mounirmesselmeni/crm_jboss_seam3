package com.insat.gl5.crm_pfa.enumeration;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public enum AddressType {
    TRAVAIL("Travail"), 
    DOMICILE("Domicile");

    private String displayName;

    private AddressType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
