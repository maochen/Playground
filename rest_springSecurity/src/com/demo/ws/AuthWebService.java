package com.demo.ws;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Path("/auth")
@Produces(MediaType.TEXT_HTML)
public class AuthWebService {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String intro(@Context HttpServletRequest request) {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        return "User " + username + " Authenticated";
    }

    @GET
    @Path("/logout")
    @Produces(MediaType.TEXT_HTML)
    public void logout(@Context HttpServletResponse response) throws IOException {
        response.sendRedirect("/j_spring_security_logout");
    }

}
