package org.maochen.springsecurity;

import org.maochen.db.springsecurity.User;
import org.maochen.db.springsecurity.UserRepository;
import org.maochen.db.springsecurity.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by mguan on 12/29/16.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserRolesRepository userRolesRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUserName(username);
    if (null == user) {
      throw new UsernameNotFoundException("No user present with username: " + username);
    }

    user.userRoles = userRolesRepository.findRoleByUserName(username);
    return user;
  }

  public CustomUserDetailsService() {

  }

}

