package com.insat.gl5.crm_pfa.enumeration;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public enum PhoneNumberType {
    TRAVAIL("Travail"),
    PORTABLE("Portable"),
    FAX("Fax"),
    DOMICILE("Domicile");

    private String displayName;

    private PhoneNumberType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
