package com.cgvsu.rasterization.triangle;

import javafx.scene.paint.Color;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Triangle {
    private static final Color DEFAULT_COLOR_1 = Color.RED;
    private static final Color DEFAULT_COLOR_2 = Color.LIME;
    private static final Color DEFAULT_COLOR_3 = Color.BLUE;

    private static final Random rand = ThreadLocalRandom.current();

    public final Vector2f v1;
    public final Vector2f v2;
    public final Vector2f v3;
    public Color c1 = DEFAULT_COLOR_1;
    public Color c2 = DEFAULT_COLOR_2;
    public Color c3 = DEFAULT_COLOR_3;

    public Triangle(Vector2f v1, Vector2f v2, Vector2f v3, Color c1, Color c2, Color c3) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
    }

    public void randomizeVertices(float maxWidth, float maxHeight) {
        float x1 = rand.nextFloat(maxWidth);
        float y1 = rand.nextFloat(maxHeight);
        float x2 = rand.nextFloat(maxWidth);
        float y2 = rand.nextFloat(maxHeight);
        float x3 = rand.nextFloat(maxWidth);
        float y3 = rand.nextFloat(maxHeight);
        v1.set(x1, y1);
        v2.set(x2, y2);
        v3.set(x3, y3);
    }

    public void randomizeColors() {
        c1 = ColorUtils.getRandomColor();
        c2 = ColorUtils.getRandomColor();
        c3 = ColorUtils.getRandomColor();
    }

    public void resetColors() {
        c1 = DEFAULT_COLOR_1;
        c2 = DEFAULT_COLOR_2;
        c3 = DEFAULT_COLOR_3;
    }
}
