package com.b2x2.data;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Player {
    private final ToyService toyService;

    //@Scheduled(fixedRate = 5000)
    public void scheduled() {
        toyService.play();
    }

}
