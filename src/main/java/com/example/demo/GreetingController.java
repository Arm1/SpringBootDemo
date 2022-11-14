package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GreetingController {
    @Autowired
    Greeting GreetingService;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/something")
    public Greeting doSomething(@RequestParam(value = "name", defaultValue = "World") String name) {
        try {
            GreetingService.doSomething();
            return new Greeting();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // (counter.incrementAndGet(), String.format(template, name));
    }
    @PostMapping("/somethingAsync")
    public Greeting doSomethingAsync(String name) {
        try {
           GreetingService.doSomethingAsync(name);
            return new Greeting();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // (counter.incrementAndGet(), String.format(template, name));
    }

}