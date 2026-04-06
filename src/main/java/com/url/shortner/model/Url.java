package com.url.shortner.model;

import org.apache.curator.retry.RetryUntilElapsed;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Repository;


@Document(collection = "url")
public class Url {
	
	@Id
	Long id;
	
	@Field("l-url")
	String longUrl;
	
	
	public Url(Long id,String url) {
		this.id=id;
		this.longUrl=url;
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	
	public String getUrl() {
		return longUrl;
	}
	public void setUrl(String url) {
		this.longUrl=url;
		
	}

}
