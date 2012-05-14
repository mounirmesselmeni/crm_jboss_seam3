package com.insat.gl5.crm_pfa.web.util;

import javax.enterprise.context.NonexistentConversationException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.jboss.solder.exception.control.CaughtException;
import org.jboss.solder.exception.control.Handles;
import org.jboss.solder.exception.control.HandlesExceptions;
import org.jboss.solder.logging.Logger;
//import org.jboss.seam.conversation.spi.SeamConversationContext;

@HandlesExceptions
public class NavigationHandler {

	@Inject
	private Logger log;
	
//	@Inject
//	private SeamConversationContext<HttpServletRequest> seamConversationContext;
//	
//	@Inject
//	private javax.faces.application.NavigationHandler nav;
//	
//	@Inject
//	private FacesContext facesContext;
//	
//	@Inject
//	private HttpServletRequest httpServletRequest;
//	
//	void nonexistentConversationException(@Handles CaughtException<NonexistentConversationException> e)
//	{
//		log.info("Conversation doesn't exist anymore");
//		e.markHandled();
//		seamConversationContext.dissociate(httpServletRequest);
//		nav.handleNavigation(facesContext, null, "/home.xhtml");
//	}
//	
//	void viewExpiredException(@Handles CaughtException<ViewExpiredException> e)
//	{
//		log.info("View has Expired");
//		e.markHandled();
//		seamConversationContext.dissociate(httpServletRequest);
//		nav.handleNavigation(facesContext, null, "/home.xhtml");
//	}	
}
