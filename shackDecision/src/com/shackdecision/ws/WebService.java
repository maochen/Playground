package com.shackdecision.ws;


import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.view.Viewable;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class WebService {

    @Path("/{order}")
    @PUT
    @Produces("text/html")
    public String create(@PathParam("order") String order, @QueryParam("customer_name") String customerName)
    {
       
       return "Added order #" + order;
    }

    @Path("/{order}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String find(@PathParam("order") String order)
    {

       return null;
        
    }

   
    
    
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable index(@Context HttpServletRequest request) {
		request.setAttribute("obj", new String("IT Works"));
		System.out.println("/I called");
		return new Viewable("/index.jsp", null);
	}
}
