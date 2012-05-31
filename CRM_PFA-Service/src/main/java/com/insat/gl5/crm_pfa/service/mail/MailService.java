/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insat.gl5.crm_pfa.service.mail;

import java.io.Serializable;
import java.net.MalformedURLException;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.mail.Session;
import org.jboss.seam.mail.api.MailMessage;
import org.jboss.seam.mail.core.enumerations.MessagePriority;
import org.jboss.seam.mail.templating.velocity.CDIVelocityContext;
import org.jboss.seam.mail.templating.velocity.VelocityTemplate;
import org.jboss.solder.resourceLoader.ResourceProvider;

/**
 * 
 * @author Mu7ammed 3li -- mohamed.ali.affes@gmail.com --
 */
public class MailService implements Serializable{

    @Inject
    private Instance<MailMessage> mailMessage;
    @Inject
    private Instance<CDIVelocityContext> cDIVelocityContext;
    @Inject
    private ResourceProvider resourceProvider;
    @Inject
    private Instance<Session> session;
//    @Inject
//    private Person receiver;
    private Person sender = new Person("Admin", "mounirelmes@gmail.com");

    public void sendJoinInvitation(Person receiver, String code, String login) throws MalformedURLException {
        mailMessage.get().
                from(sender).
                to(receiver).
                subject("Vous avez été invité à joindre CRM-PFA").
                bodyHtml(new VelocityTemplate(resourceProvider.loadResourceStream("templateInvitation.html.velocity"), cDIVelocityContext.get())).
                put("receiver", receiver).
                put("sender", sender).
                put("activationCode", code).
                put("login", login).
                importance(MessagePriority.HIGH).
                send(session.get());
    }
}
