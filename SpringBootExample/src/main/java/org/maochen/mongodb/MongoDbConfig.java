package org.maochen.mongodb;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 * Created by mguan on 11/23/16.
 */
@Configuration
@EnableMongoRepositories
public class MongoDbConfig extends AbstractMongoConfiguration {

  @Value("${spring.data.mongodb.host:localhost}")
  private String hostAddress;

  @Value("${spring.data.mongodb.port:27017}")
  private int port;

  @Value("${spring.data.mongodb.username:admin}")
  private String userName;

  @Value("${spring.data.mongodb.password:admin}")
  private String pw;

  private List<MongoCredential> credentials;

  /**
   * post constructor, setup credentials.
   */
  @PostConstruct
  public void init() {
    MongoCredential mongoCredential = MongoCredential.createMongoCRCredential(userName, getDatabaseName(), pw.toCharArray());
    credentials = new ArrayList<>();
    credentials.add(mongoCredential);
  }

  @Bean
  public MongoTemplate mongoTemplate() throws Exception {
    return new MongoTemplate(mongo(), getDatabaseName());
  }

  @Override
  protected String getDatabaseName() {
    return "example_db";
  }

  @Override
  @Bean
  public Mongo mongo() throws Exception {
    ServerAddress address = new ServerAddress(hostAddress, port);
    // TODO: Disable credentials for now.
    // Mongo mongo = new MongoClient(address, credentials);
    Mongo mongo = new MongoClient(address);
    return mongo;
  }
}
