/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.PieChartModel;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
@Named
@RequestScoped
public class ChartBean implements Serializable {

    private PieChartModel pieModelAchat;
    private PieChartModel pieModelVente;
    private MeterGaugeChartModel meterGaugeModel;
    private CartesianChartModel categoryModel;
    private CartesianChartModel categoryModelClient;

    /**
     * Creates a new instance of ChartBean
     */
    public ChartBean() {
        createPieModelAchat();
        createPieModelVente();
        createMeterGaugeModel();
        createCategoryModel();
        createCategoryClientModel();
    }

    public PieChartModel getPieModelAchat() {
        return pieModelAchat;
    }

    public PieChartModel getPieModelVente() {
        return pieModelVente;
    }

    public MeterGaugeChartModel getMeterGaugeModel() {
        return meterGaugeModel;
    }

    public CartesianChartModel getCategoryModel() {
        return categoryModel;
    }

    public CartesianChartModel getCategoryModelClient() {
        return categoryModelClient;
    }
    
    
    
    private void createPieModelAchat() {
        pieModelAchat = new PieChartModel();

        pieModelAchat.set("Demandes", 43);
        pieModelAchat.set("Attente de validation", 5);
        pieModelAchat.set("En commande", 12);
    }

    private void createPieModelVente() {
        pieModelVente = new PieChartModel();

        pieModelVente.set("En cours", 25);
        pieModelVente.set("A relancer", 7);
        pieModelVente.set("Terminée", 43);
    }

    private void createMeterGaugeModel() {
        List<Number> intervals = new ArrayList<Number>() {

            {
                add(40);
                add(70);
                add(90);
                add(100);
            }
        };
        meterGaugeModel = new MeterGaugeChartModel("% CA Atteint", 68, intervals);
    }

    private void createCategoryModel() {
        categoryModel = new CartesianChartModel();

        ChartSeries produits = new ChartSeries();
        produits.setLabel("Produits");

        produits.set("Etude", 10);
        produits.set("Production", 75);
        produits.set("Fin de vie", 3);

        ChartSeries services = new ChartSeries();
        services.setLabel("Services");

        services.set("Etude", 5);
        services.set("Production", 25);
        services.set("Fin de vie", 10);

        categoryModel.addSeries(produits);
        categoryModel.addSeries(services);    
    }

    private void createCategoryClientModel() {
        categoryModelClient = new CartesianChartModel();

        ChartSeries client = new ChartSeries();
        client.setLabel("Clients");

        client.set("Propects", 70);
        client.set("Standard", 35);
        client.set("Premium", 6);

        categoryModelClient.addSeries(client);
    }
}
