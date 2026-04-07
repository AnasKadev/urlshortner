package com.url.shortner.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.url.shortner.service.Base62;
import com.url.shortner.service.RangeIdService;
import com.url.shortner.service.UrlService;

@RestController
public class UrlController {

    private final UrlService urlservice;

    public UrlController(UrlService urlService) {
        this.urlservice = urlService;
    }
    
    
    @PostMapping("/short-url")
    public String generateShortUrl(@RequestParam String url) {
        return urlservice.shortenUrl(url);
    }
}
