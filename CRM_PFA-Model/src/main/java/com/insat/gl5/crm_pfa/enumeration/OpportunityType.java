package com.insat.gl5.crm_pfa.enumeration;


/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public enum OpportunityType{

    NEGOCIATION("Negociation"), 
    PROSPECTION("Prospection"),
    PROPOSITION("Proposition");

    private String displayName;

    private OpportunityType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
