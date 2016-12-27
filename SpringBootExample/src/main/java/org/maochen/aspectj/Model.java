package org.maochen.aspectj;

import org.springframework.stereotype.Component;

/**
 * Created by mguan on 12/27/16.
 */

@Component
public class Model {
  public void example() {
    System.err.println("Call example()");
  }

  public void throwException() throws RuntimeException {
    throw new RuntimeException("Example Exception");
  }
}
