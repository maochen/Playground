package org.maochen.ws;

import org.maochen.db.CustomerDto;
import org.maochen.db.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/rest")
public class RestWebService {

  @Autowired
  private CustomerRepository customerRepository;

  /**
   * PUT DEMO
   *
   * @return String.
   */
  @RequestMapping(produces = MediaType.TEXT_HTML_VALUE, value = "/{first_name}", method = RequestMethod.PUT)
  public String create(@PathVariable("first_name") String firstName, @RequestParam("last_name") String lastName, HttpServletRequest request) {
    if (firstName == null || firstName.trim().isEmpty() || lastName == null || lastName.trim().isEmpty()) {
      throw new RuntimeException("Invalid first name: " + firstName + "\tlast name: " + lastName);
    }

    CustomerDto dto = new CustomerDto(firstName, lastName);
    customerRepository.save(dto);
    return "Added " + dto + System.lineSeparator();
  }

  /**
   * list all customer dtos.
   *
   * @return all dtos.
   */
  @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/list", method = RequestMethod.GET)
  public String list() {
    Iterable<CustomerDto> iterable = customerRepository.findAll();
    return StreamSupport.stream(iterable.spliterator(), false)
        .map(CustomerDto::toString)
        .collect(Collectors.joining(System.lineSeparator()));
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
