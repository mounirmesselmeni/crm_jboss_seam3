/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.web.controller;

import java.io.Serializable;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import org.jboss.solder.logging.Logger;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public class ConversationController implements Serializable {

    @Inject
    private Conversation conversation;
    @Inject
    private Logger log;

    /**
     * Init conversation
     */
    public void beginConversation() {
        if (conversation.isTransient()) {
            getConversation().begin();
        }
    }

    /*
     * Fermer la conversation
     */
    public void endConversation() {
        if (!conversation.isTransient()) {
            getConversation().end();
        }
    }

     /**
     * Close conversation
     * @param redirect
     * @return 
     */
    public void cancel() {
        endConversation();
    }
    /**
     * Fermer la conversation et redirection vers la page redirect
     * @param redirect la page de destination
     * @return la page de destination
     */
    public String cancel(String redirect) {
        endConversation();
        return redirect;
    }

    /**
     * @return the conversation
     */
    public Conversation getConversation() {
        return conversation;
    }

    /**
     * @param conversation the conversation to set
     */
    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

}
