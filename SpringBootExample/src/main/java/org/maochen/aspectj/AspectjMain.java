package org.maochen.aspectj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by mguan on 12/27/16.
 */
@SpringBootApplication
public class AspectjMain implements CommandLineRunner {

  @Autowired
  private Model model;

  @Override
  public void run(String... strings) throws Exception {
    model.example();
    model.throwException();
  }


  public static void main(String[] args) throws Exception {
    SpringApplication.run(AspectjMain.class, args);
  }
}
