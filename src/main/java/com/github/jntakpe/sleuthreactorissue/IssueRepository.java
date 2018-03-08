package com.github.jntakpe.sleuthreactorissue;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends ReactiveMongoRepository<Issue, ObjectId> {

}
