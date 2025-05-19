package com.b2x2.client;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Game {
    private final Set<Fish> fishes;

    public Game() {
        fishes = new HashSet<>();

        for (int i = 0; i < 100; i++) {
            Fish fish = new Fish();
            fishes.add(fish);
        }
    }

    public void logic(double delta) {
        fishes.forEach(fish -> fish.logic(delta));
    }

    public List<Renderable> getRenderables() {
        return fishes.stream().map(Fish::render).filter(Objects::nonNull).toList();
    }
}
