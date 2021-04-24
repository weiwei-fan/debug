package com.s21.sda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BookFinderGoogleAdaptee {

    @Autowired
    private RestTemplate restTemplate;

    public String findBookOnGoogle(String keyword) {
        String url = "https://www.googleapis.com/books/v1/volumes";
        url = url + "?q=" + keyword;
        String resultStr = restTemplate.getForObject(url, String.class);
        return resultStr;
    }

}
