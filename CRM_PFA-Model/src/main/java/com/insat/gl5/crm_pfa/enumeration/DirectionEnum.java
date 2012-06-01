package com.insat.gl5.crm_pfa.enumeration;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public enum DirectionEnum {
    TO_BACKUSER("To BackUser"), 
    FROM_BACKUSER("From BackUser");

    private String displayName;

    private DirectionEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
