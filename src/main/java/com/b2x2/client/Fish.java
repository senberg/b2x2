package com.b2x2.client;

import java.util.concurrent.ThreadLocalRandom;

public class Fish {
    double x = ThreadLocalRandom.current().nextDouble(1);
    double y = ThreadLocalRandom.current().nextDouble(1);
    boolean flip = ThreadLocalRandom.current().nextBoolean();
    double speed = ThreadLocalRandom.current().nextDouble(0.7, 1.3) / 10000;

    public void logic(double delta) {
        while (delta > 0) {
            double diff = Math.min(delta, 6);
            delta -= diff;

            if (flip) {
                x -= diff * speed;

                if (x <= 0) {
                    flip = false;
                }
            } else {
                x += diff * speed;

                if (x >= 1) {
                    flip = true;
                }
            }
        }

    }

    public Renderable render() {
        String src = flip ? "fish.png" : "fishright.png";
        return new Renderable(src, x, y, 128, 128);
    }
}
