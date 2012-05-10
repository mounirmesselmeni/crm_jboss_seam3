package com.insat.gl5.crm_pfa.enumeration;


/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public enum TaskType{

    APPEL("Appel téléphonique"),
    FAX("Fax"),
    EMAIL("e-mail"),
    LETTRE("Lettre"),
    REUNION("Réunion"),
    AUTRE("Autre");

    private String displayName;

    private TaskType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
