package com.shakedecision.ws;

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

    @Path("/set")
    @PUT
    @Produces("text/html")
    public String setData(@QueryParam("timestamp") String timestamp, @QueryParam("token") String token, @QueryParam("location") String location,
            @QueryParam("isWorking") boolean isWorking, @QueryParam("cost") double cost) {

        return "success";
    }

    @Path("/{order}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String find(@PathParam("order") String order) {

        return null;

    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable intro(@Context HttpServletRequest request) {
        request.setAttribute("obj", new String("IT Works"));
        System.out.println("/I called");    
        return new Viewable("/index.html", null);
    }
}
