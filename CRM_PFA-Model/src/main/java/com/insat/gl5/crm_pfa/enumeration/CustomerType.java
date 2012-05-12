package com.insat.gl5.crm_pfa.enumeration;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public enum CustomerType {
	CLIENT("Client"),
	FOURNISSEUR("Fournisseur"),
	PROSPECT("Prospect"),
        PARTENAIRE("Partenaire");

	private String displayName;

	private CustomerType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
