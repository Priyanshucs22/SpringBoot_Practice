package com.example.external_api_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class SubscriptionService {

    @Autowired
    private RestTemplate restTemplate;

    public String subscribeUser(Map<String, Object> request) {

        String url = "https://jsonplaceholder.typicode.com/posts";

        return restTemplate.postForObject(url, request, String.class);
    }
}
