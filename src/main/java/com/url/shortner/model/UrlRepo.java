package com.url.shortner.model;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UrlRepo extends MongoRepository<Url, Long> {
	@Query(value = "{'id': ?0}",fields="{ 'longUrl' : 1, '_id' : 0 }")
	Optional<String> findByShortUrl(String id);

}
