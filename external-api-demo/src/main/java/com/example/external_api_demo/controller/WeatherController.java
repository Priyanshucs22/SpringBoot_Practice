package com.example.external_api_demo.controller;

import com.example.external_api_demo.service.SubscriptionService;
import com.example.external_api_demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private SubscriptionService subscriptionService;

    // GET API
    @GetMapping("/{city}")
    public String getWeather(@PathVariable String city) {
        return weatherService.getWeather(city);
    }

    // POST API
    @PostMapping("/subscribe")
    public String subscribe(@RequestBody Map<String, Object> request) {
        return subscriptionService.subscribeUser(request);
    }
}
