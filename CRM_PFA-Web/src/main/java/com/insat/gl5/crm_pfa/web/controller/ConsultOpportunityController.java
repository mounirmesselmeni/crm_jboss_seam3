/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller;

import com.insat.gl5.crm_pfa.model.Opportunity;
import com.insat.gl5.crm_pfa.service.OpportunityService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author
 * Mu7ammed
 * 3li
 * --
 * mohamed.ali.affes@gmail.com
 * --
 */
@Named
@SessionScoped
public class ConsultOpportunityController implements Serializable {

    @Inject
    private OpportunityService opportunityService;
    private Long id;
    private Opportunity opportunity;

    public void initOpportunity(){
        opportunity = opportunityService.findById(id);
    }
    /**
     * @return
     * the
     * opportunity
     */
    public Opportunity getOpportunity() {
        return opportunity;
    }

    /**
     * @param
     * opportunity
     * the
     * opportunity
     * to
     * set
     */
    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }

    /**
     * @return
     * the
     * id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param
     * id
     * the
     * id
     * to
     * set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
