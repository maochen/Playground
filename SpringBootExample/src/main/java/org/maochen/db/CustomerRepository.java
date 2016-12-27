package org.maochen.db;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mguan on 12/27/16.
 */
public interface CustomerRepository extends CrudRepository<CustomerDto, Long> {

  List<CustomerDto> findByLastName(String lastName);
}