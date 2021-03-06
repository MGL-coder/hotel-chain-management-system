package com.bmmzz;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/authorization")
public class AuthorizationService {
	
	@Context ServletContext servletContext;
	@Context UriInfo uri;
	
	public AuthorizationService() {
		UserDAO.connectToUserDAO();
	}
	
	@GET
	@Produces({MediaType.TEXT_HTML})
	public InputStream get() {	
		return Helper.getPage(servletContext, "authorizationPage.html");
	}
	
	@POST
	public String authorization(@FormParam("username") String username, 
							    @FormParam("password") String password) {
		String auth = UserDAO.getEncodedAuth(username, password);
		if(UserDAO.checkAuth(auth)) {
			return uri.getBaseUri().toString() + "profile" + "?auth=" + auth;
		}
		return null;
	}
}
