package org.maochen.db.springsecurity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import javax.persistence.*;

/**
 * Created by mguan on 12/29/16.
 */
@Entity
@Table(name = "users")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "userid")
  private Long userId;

  @Column(name = "username")
  private String userName;

  @Column(name = "password")
  private String password;

  @Column(name = "email")
  private String email;

  @Column
  private boolean locked;

  @Column(name = "enabled")
  private boolean enabled;

  @Transient
  public List<String> userRoles;


  /**
   * For spring.
   */
  private User() {

  }

  /**
   * Constructor
   *
   * @param user user.
   */
  public User(User user) {
    this.userId = user.userId;
    this.userName = user.userName;
    this.email = user.email;
    this.password = user.password;
    this.locked = user.locked;
    this.enabled = user.enabled;
  }

  @Transient
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
    return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
  }

  @Transient
  @Override
  public String getPassword() {
    return this.password;
  }

  @Transient
  @Override
  public String getUsername() {
    return this.userName;
  }

  @Transient
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Transient
  @Override
  public boolean isAccountNonLocked() {
    return !this.locked;
  }

  @Transient
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Transient
  @Override
  public boolean isEnabled() {
    return this.enabled;
  }
}