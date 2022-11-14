package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@EnableAsync
@Service
public class Greeting {

    Logger logger = LoggerFactory.getLogger(Greeting.class);
    static volatile int count = 0;

    private final long id;
    private final String content;

//    public Greeting(long id, String content) {
//        this.id = id;
//        this.content = content;
//    }
    public Greeting()
    {
        this.id = 1;
        this.content = "Hello";
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @JsonIgnore
    public Greeting doSomething() throws InterruptedException {
        Thread.sleep(1000);
        logger.info("test");
        return new Greeting();
    }
    @Async("streetCheckerExecutor")
    @JsonIgnore
    public Greeting doSomethingAsync(String test) throws InterruptedException {
        Thread.sleep(10);
        count ++;
        logger.info("test: " + count);
        return new Greeting();
    }

}