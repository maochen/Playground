package org.maochen.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by mguan on 12/27/16.
 */
public interface DtoMongoRepository extends MongoRepository<MongoDto, String> {
}
