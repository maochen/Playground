package org.maochen.ws;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/rest")
public class WebService {

  private static Map<String, String> orderData = new ConcurrentHashMap<>();

  /**
   * PUT DEMO
   *
   * @return String.
   */
  @RequestMapping(produces = MediaType.TEXT_HTML_VALUE, value = "/{val}", method = RequestMethod.PUT)
  public String create(@PathVariable("val") String val, @RequestParam("customer_name") String customerName, HttpServletRequest request) {
    if (customerName == null || customerName.trim().isEmpty()) {
      customerName = request.getRemoteAddr() + "_" + request.getRemotePort();
    }

    orderData.put(customerName, val);
    return "Added order " + val + " for " + customerName + System.lineSeparator();
  }

  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/list", method = RequestMethod.GET)
  public String list() {
    return orderData.toString() + System.lineSeparator();
  }

  /**
   * HTTP ServletRequest.
   *
   * @return String.
   */
  @RequestMapping(produces = MediaType.TEXT_HTML_VALUE, value = "/demo", method = RequestMethod.GET)
  public String index(HttpServletRequest request) {
    request.setAttribute("obj", "IT Works");
    System.out.println("/I called");
    return "DEMO";
  }
}
