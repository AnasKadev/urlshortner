package com.url.shortner.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.url.shortner.model.Url;
import com.url.shortner.model.UrlRepo;

@Service
public class UrlService {

	private UrlRepo urlRepo;
	private final RangeIdService idService;

	public UrlService(UrlRepo urlRepo, RangeIdService idService) {
		this.idService=idService;
		this.urlRepo = urlRepo;
	}

	public static final String BASE_URL = "https://ak.sh";

	public Optional<String> getUrlByShort(String shorturl) {
		String urlidString = shorturl.substring(13);
		return urlRepo.findByShortUrl(urlidString);
	}
	
	public String shortenUrl(String longurl) {
		long id=idService.nextId();
		String hash=Base62.encode(id);
		String shorturl = BASE_URL+hash;
		urlRepo.insert(new Url(id, shorturl));
		
		return shorturl;
	}
}
