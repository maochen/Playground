package org.maochen.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

/**
 * Created by mguan on 12/27/16.
 */
@Document(collection = "mongo_example_dto")
public class MongoDto {
  @NotNull
  @Id
  public int id;

  @Field("dto_name")
  @Indexed
  public String name;
}
