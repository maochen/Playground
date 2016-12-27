package org.maochen.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by mguan on 12/27/16.
 */
@SpringBootApplication
public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

  @Bean
  public CommandLineRunner demo(CustomerRepository customerRepository) {
    return (args) -> {
      // save a couple of customers
//      customerRepository.save(new CustomerDto("Jack", "Bauer"));
//      customerRepository.save(new CustomerDto("Chloe", "O'Brian"));
//      customerRepository.save(new CustomerDto("Kim", "Bauer"));
//      customerRepository.save(new CustomerDto("David", "Palmer"));
//      customerRepository.save(new CustomerDto("Michelle", "Dessler"));

      // fetch all customers
      log.info("Customers found with findAll():");
      log.info("-------------------------------");
      for (CustomerDto customer : customerRepository.findAll()) {
        log.info(customer.toString());
      }
      log.info("");

      // fetch an individual customer by ID
      CustomerDto customer = customerRepository.findOne(1L);
      log.info("Customer found with findOne(1L):");
      log.info("--------------------------------");
      log.info(customer.toString());
      log.info("");

      // fetch customers by last name
      log.info("Customer found with findByLastName('Bauer'):");
      log.info("--------------------------------------------");
      for (CustomerDto bauer : customerRepository.findByLastName("Bauer")) {
        log.info(bauer.toString());
      }
      log.info("");
    };
  }


}
