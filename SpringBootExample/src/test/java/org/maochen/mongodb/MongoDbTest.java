package org.maochen.mongodb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;

/**
 * Created by mguan on 11/22/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MongoDbConfig.class)
public class MongoDbTest {

  @Autowired
  public MongoTemplate mongoTemplate;

  @Autowired
  private DtoMongoRepository dtoMongoRepository;


  @Test
  public void testDbName() {
    String dbName = mongoTemplate.getDb().getName();
    assertNotNull(dbName);
    assertTrue(!dbName.isEmpty());
  }

  @Test
  public void testDtoWrite() {
    MongoDto expectedDto = new MongoDto();
    expectedDto.id = 1;
    expectedDto.name = "example";

    dtoMongoRepository.save(expectedDto);

    Iterator<MongoDto> foundIter = dtoMongoRepository.findAll().iterator();
    assertTrue(foundIter.hasNext());
    MongoDto foundDto = foundIter.next();

    assertEquals(expectedDto.id, foundDto.id);
    assertEquals(expectedDto.name, foundDto.name);
  }

  @After
  public void tearDown() {
    dtoMongoRepository.deleteAll();
  }

}
