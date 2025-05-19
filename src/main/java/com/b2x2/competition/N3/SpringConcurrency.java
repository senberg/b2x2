package com.b2x2.competition.N3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import static java.lang.System.out;

@Component
public class SpringConcurrency {
    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Scheduled(fixedRate = 1000)
    public void scheduledTask() {
        executor.submit(() -> out.println("Ping"));
    }
}

