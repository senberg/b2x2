package com.b2x2.client;

public class Client {
    public static void main(String[] args) {
        ImageStore imageStore = new ImageStore();
        Game game = new Game();
        RenderingEngine renderingEngine = new RenderingEngine(game, imageStore);
    }
}
