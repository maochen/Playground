package org.maochen.ws;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by mguan on 10/26/16.
 */
@ApplicationPath("/")
public class WebApplication extends ResourceConfig {
  public WebApplication() {
    packages("org.maochen.ws");
  }
}
