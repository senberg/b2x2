package com.b2x2.client;

public class Fish {
    double x = Rng.randomPercentage();
    double y = Rng.randomPercentage();
    boolean flip = Rng.randomBoolean();
    double speed = Rng.randomDouble(0.7, 1.3) / 10000;

    public void logic(double delta) {
        while(delta > 0) {
            double diff = Math.min(delta, 6);
            delta -= diff;

            if(flip) {
                x -= diff * speed;

                if(x <= 0) {
                    flip = false;
                }
            } else {
                x += diff * speed;

                if(x >= 1) {
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
