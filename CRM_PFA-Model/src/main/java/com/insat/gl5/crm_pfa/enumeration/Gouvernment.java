package com.insat.gl5.crm_pfa.enumeration;
/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public enum Gouvernment {
	TUNIS("Tunis"),
	SFAX("Sfax");

	private String displayName;

	private Gouvernment(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

}
