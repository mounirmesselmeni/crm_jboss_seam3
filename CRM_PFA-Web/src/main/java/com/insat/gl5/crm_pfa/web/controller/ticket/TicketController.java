/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller.ticket;

import com.insat.gl5.crm_pfa.model.Ticket;
import com.insat.gl5.crm_pfa.web.controller.ConversationController;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.jboss.seam.international.status.Messages;

/**
 *
 * @author Mounir Messelmeni <messelmeni.mounir@gmail.com>
 */
@Named
@ConversationScoped
public class TicketController extends ConversationController{
    @Inject
    private Ticket ticket;
    @Inject
    private Messages messages;
    
}
