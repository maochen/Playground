package com.shakedecision.ws;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

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

    private String readFile(String filepath) {
        StringBuffer buf = new StringBuffer();
        try {

            FileInputStream fstream = new FileInputStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                buf.append(strLine);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buf.toString();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String intro(@Context HttpServletRequest request) {
        request.setAttribute("obj", new String("IT Works"));
        System.out.println("/I called");
        return readFile("/html/index.html");
        
        // return new Viewable("/html/index.html", null);
    }
}
