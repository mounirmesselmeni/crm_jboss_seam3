/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Named
@RequestScoped
public class ImagesList {

    private List<String> images;

    /**
     * Creates a new instance of ListeImages
     */
    public ImagesList() {
        // Initialisation des images
        images = new ArrayList<String>();
        images.add("Contact.png");
        images.add("Vente.png");
        images.add("achats.png");
        images.add("banque.png");
        images.add("categorie.png");
        images.add("comptabilite.png");
        images.add("compte.png");
        images.add("facture.png");
        images.add("produit.png");
        images.add("references.png");
        images.add("service.png");
        images.add("statut.png");
        images.add("tiers.png");
    }

    public List<String> getImages() {
        return images;
    }
}
