package com.teb.loghandler.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.teb.loghandler.model.Log;

public interface LogRepository extends MongoRepository<Log, String> {

}
