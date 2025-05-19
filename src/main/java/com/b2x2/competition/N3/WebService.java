package com.b2x2.competition.N3;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
public class WebService {
    @Autowired
    private ThreadPoolTaskExecutor executor;

    class PingData {
        public Long time;
    }

    @GetMapping("/ping")
    public PingData ping() {
        PingData data = new PingData();
        data.time = System.currentTimeMillis();
        executor.submit(() -> log.info("Ping endpoint was polled at " + data.time));
        return data;
    }
}
