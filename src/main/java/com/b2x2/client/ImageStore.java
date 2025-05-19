package com.b2x2.client;

import org.teavm.jso.dom.html.HTMLDocument;
import org.teavm.jso.dom.html.HTMLImageElement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ImageStore {
    private final Map<String, ImageContainer> images = new HashMap<>();
    private final HTMLDocument document;

    public ImageStore() {
        document = HTMLDocument.current();
    }

    public Optional<HTMLImageElement> getImage(String src) {
        ImageContainer imageContainer = images.computeIfAbsent(src, k -> new ImageContainer(document, src));

        if(imageContainer.loaded) {
            return Optional.of(imageContainer.image);
        } else {
            return Optional.empty();
        }
    }

    private static class ImageContainer {
        boolean loaded;
        HTMLImageElement image;

        public ImageContainer(HTMLDocument document, String src) {
            image = (HTMLImageElement) document.createElement("img");
            image.onEvent("load", _ -> loaded = true);
            image.setSrc(src);
        }
    }
}
