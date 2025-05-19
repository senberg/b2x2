package com.b2x2.client;

import org.teavm.jso.browser.AnimationFrameCallback;
import org.teavm.jso.browser.Performance;
import org.teavm.jso.browser.Window;
import org.teavm.jso.canvas.CanvasRenderingContext2D;
import org.teavm.jso.dom.html.HTMLBodyElement;
import org.teavm.jso.dom.html.HTMLCanvasElement;
import org.teavm.jso.dom.html.HTMLDocument;

public class RenderingEngine implements AnimationFrameCallback {
    public static final String VIEW_CANVAS_ID = "v";

    private final Game game;
    private final ImageStore imageStore;
    private final Window window;
    private final HTMLDocument document;
    private final HTMLCanvasElement viewCanvas;
    private final CanvasRenderingContext2D viewCanvascontext;
    private final double starttime;

    private int viewWidth;
    private int viewHeight;

    private double lastFrameTime;

    public RenderingEngine(Game game, ImageStore imageStore) {
        this.game = game;
        this.imageStore = imageStore;
        window = Window.current();
        viewWidth = window.getInnerWidth();
        viewHeight = window.getInnerHeight();
        document = HTMLDocument.current();
        HTMLBodyElement body = document.getBody();
        viewCanvas = (HTMLCanvasElement) document.createElement("canvas", canvas -> {
            canvas.setId(VIEW_CANVAS_ID);
            canvas.setAttribute("width", Integer.toString(viewWidth));
            canvas.setAttribute("height", Integer.toString(viewHeight));
        });
        window.onEvent("resize", event -> {
            viewWidth = window.getInnerWidth();
            viewHeight = window.getInnerHeight();
            viewCanvas.setAttribute("width", Integer.toString(viewWidth));
            viewCanvas.setAttribute("height", Integer.toString(viewHeight));
        });
        viewCanvascontext = (CanvasRenderingContext2D) viewCanvas.getContext("2d");
        body.appendChild(viewCanvas);

        starttime = Performance.now();
        lastFrameTime = starttime;
        Window.requestAnimationFrame(this);
    }

    @Override
    public void onAnimationFrame(double timestamp) {
        double delta = timestamp - lastFrameTime;
        int fps = (int) (1000 / delta);
        document.setTitle("b2x2 (" + fps + " fps)");
        game.logic(delta);
        viewCanvas.clear();

        viewCanvascontext.setFillStyle("rgb(0.3 0.3 " + (0.3 + Math.sin(timestamp / 20)) + ")");
        viewCanvascontext.fillRect(0, 0, window.getInnerWidth(), window.getInnerHeight());

        game.getRenderables().forEach(renderable -> imageStore.getImage(renderable.src()).ifPresent(image -> {
            int viewX = (int) (viewWidth * renderable.x());
            int viewY = (int) (viewHeight * renderable.y());
            viewCanvascontext.drawImage(image, viewX, viewY, renderable.width(), renderable.height());
        }));

        lastFrameTime = timestamp;
        Window.requestAnimationFrame(this);
    }
}
