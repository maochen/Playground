package org.maochen.db;

import org.h2.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

/**
 * Created by mguan on 12/27/16.
 */
@Configuration
public class H2DbConfig {
  /**
   * H2 Data source.
   *
   * @return data source.
   */
  @Bean
  public DataSource dataSource() {
    Driver h2Driver = new Driver();
    String url = "jdbc:h2:~/Desktop/testdb";
    DataSource dataSource = new SimpleDriverDataSource(h2Driver, url);
    // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
    //    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    //    EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).setName("h2_example").build();
    return dataSource;
  }

  @Bean
  public JdbcTemplate jdbcTemplate() {
    JdbcTemplate template = new JdbcTemplate(dataSource());
    return template;
  }
}
