package org.maochen.ws;

import org.glassfish.jersey.server.mvc.Viewable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class WebService {

    private static Map<String, String> orderData = new ConcurrentHashMap<>();

    @Path("/place")
    @PUT
    @Produces("text/html")
    public String create(@PathParam("val") String val, @QueryParam("customer_name") String customerName, @Context HttpServletRequest request) {
        if (customerName == null || customerName.trim().isEmpty()) {
            customerName = request.getRemoteAddr() + "_" + request.getRemotePort();
        }

        orderData.put(customerName, val);
        return "Added order " + val + " for " + customerName + System.lineSeparator();
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String list() {
        return orderData.toString() + System.lineSeparator();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable index(@Context HttpServletRequest request) {
        request.setAttribute("obj", "IT Works");
        System.out.println("/I called");
        return new Viewable("/index.jsp", null);
    }
}
