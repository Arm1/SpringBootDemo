package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Component
public class QueueChecker
{
    Logger logger = LoggerFactory.getLogger(QueueChecker.class);

    @Autowired
    @Qualifier("streetCheckerExecutor")
    private Executor streetExecutor;

    @EventListener(ApplicationReadyEvent.class)
    public void checkStreets() {
        while (true) {
            try {
                final BlockingQueue<Runnable> queue = ((ThreadPoolTaskExecutor)streetExecutor).getThreadPoolExecutor().getQueue();
                logger.info("queue : " + queue.size());
            } catch (Exception e) {
                logger.error("error ", e);
            } finally {
                try {
                    Thread.sleep(1000 );
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
