package org.maochen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by mguan on 12/27/16.
 */
@SpringBootApplication
public class DemoSpringBootApp extends SpringBootServletInitializer {
  public static void main(String[] args) {
    SpringApplication.run(DemoSpringBootApp.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(this.getClass());
  }
}
