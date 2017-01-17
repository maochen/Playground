package org.maochen.db;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;

/**
 * Created by mguan on 1/16/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = H2DbConfig.class)
public class H2DbTest {

  @Autowired
  private CustomerRepository customerRepository;

  @Test
  public void test() {
    CustomerDto dto = new CustomerDto("f", "l");
    customerRepository.save(dto);

    Iterator<CustomerDto> iter = customerRepository.findAll().iterator();
    int count = 0;
    while (iter.hasNext()) {
      iter.next();
      count++;
    }

    assertEquals(1, count);
  }
}
