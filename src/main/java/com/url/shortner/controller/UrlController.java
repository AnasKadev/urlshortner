package com.url.shortner.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.url.shortner.service.Base62;
import com.url.shortner.service.RangeIdService;

@RestController
public class UrlController {

    private final RangeIdService idService;

    public UrlController(RangeIdService idService) {
        this.idService = idService;
    }
    
    
    @PostMapping("/short-url")
    public String generateShortUrl() {
        long id = idService.nextId();
        return Base62.encode(id);
    }
}
