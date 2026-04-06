package com.url.shortner.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepo extends MongoRepository<Url, Long> {
	

}
