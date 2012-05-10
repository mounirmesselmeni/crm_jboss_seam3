package com.insat.gl5.crm_pfa.enumeration;


/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public enum PriorityType{

    BASSE("Basse"), 
    NORMALE("Normale"),
    HAUTE("Haute"),
    URGENTE("Urgente"),
    IMMEDIATE("Immediate");

    private String displayName;

    private PriorityType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
