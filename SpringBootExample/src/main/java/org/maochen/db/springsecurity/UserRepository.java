package org.maochen.db.springsecurity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mguan on 12/29/16.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  public User findByUserName(String username);
}
