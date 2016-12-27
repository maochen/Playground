package org.maochen.aspectj;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by mguan on 12/27/16.
 */
@SpringBootApplication
public class Application {


  @Bean
  public CommandLineRunner run(Model model) throws Exception {
    return (args) -> {
      model.example();
      //      model.throwException();
    };
  }


  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }
}
