package org.maochen.ws;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * Created by mguan on 11/29/16.
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter implements EmbeddedServletContainerCustomizer {

  @Override
  public void customize(ConfigurableEmbeddedServletContainer container) {
    container.setSessionTimeout(10, TimeUnit.MINUTES);
  }

  //  @Override
  //  public void addResourceHandlers(ResourceHandlerRegistry registry) {
  //    if (!registry.hasMappingForPattern("/**")) {
  //      registry.addResourceHandler("/**")
  //        .addResourceLocations("file:war/")
  //        .addResourceLocations("classpath:/../../");
  //      super.addResourceHandlers(registry);
  //    }
  //  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    //    registry.addViewController("/").setViewName("forward:/settings.html");
  }
}
