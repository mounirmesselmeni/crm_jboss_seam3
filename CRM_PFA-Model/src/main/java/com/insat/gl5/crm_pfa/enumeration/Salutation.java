package com.insat.gl5.crm_pfa.enumeration;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public enum Salutation {

    MR("Mr", "Monsieur"),
    MME("Mme", "Madame"),
    MLLE("Mlle", "Mademoiselle");

    private String shortName;
    private String displayName;

    private Salutation(String shortName, String displayName) {
        this.shortName = shortName;
        this.displayName = displayName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
