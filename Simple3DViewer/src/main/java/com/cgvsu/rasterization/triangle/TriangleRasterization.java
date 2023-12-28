package com.cgvsu.rasterization.triangle;

import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Comparator;

public class TriangleRasterization {
    /**
     * A comparator used to sort the vertices of a triangle.
     */
    private static final Comparator<Vector2f> COMPARATOR = (a, b) -> {
        int cmp = Float.compare(a.y, b.y);
        if (cmp != 0) {
            return cmp;
        } else return Float.compare(a.x, b.x);
    };

    /**
     * A mutable vector used to speed up calculations.
     */
    private static final Vector2f p = new Vector2f();

    /**
     * A mutable color used to avoid creating new objects in loops.
     */
    private static final MutableColor color = new MutableColor();

    /**
     * Draws the specified triangle.
     * @param pw The pixel writer.
     * @param trig The triangle to draw.
     */
    public static void drawTriangle(PixelWriter pw, Triangle trig) {
        drawTriangle(pw, trig.v1, trig.v2, trig.v3, trig.c1, trig.c2, trig.c3);
    }

    /**
     * Draws the specified triangle. The order of the vertices is irrelevant.
     * @param pw The pixel writer.
     * @param v1 The first vertex.
     * @param v2 The second vertex.
     * @param v3 The third vertex.
     * @param c1 The first color.
     * @param c2 The second color.
     * @param c3 The third color.
     */
    public static void drawTriangle(
            final PixelWriter pw,
            final Vector2f v1,
            final Vector2f v2,
            final Vector2f v3,
            final Color c1,
            final Color c2,
            final Color c3
    ) {
        // Sort vertices by y.
        final var verts = new Vector2f[]{v1, v2, v3};
        Arrays.sort(verts, COMPARATOR);
        final int x1 = (int) verts[0].x;
        final int x2 = (int) verts[1].x;
        final int x3 = (int) verts[2].x;
        final int y1 = (int) verts[0].y;
        final int y2 = (int) verts[1].y;
        final int y3 = (int) verts[2].y;

        // Double the area of the triangle. Used to calculate the barycentric coordinates later.
        final float area = Math.abs(v1.to(v2).crossMagnitude(v1.to(v3)));
        drawTopTriangle(pw, x1, y1, x2, y2, x3, y3, v1, c1, v2, c2, v3, c3, area);
        drawBottomTriangle(pw, x1, y1, x2, y2, x3, y3, v1, c1, v2, c2, v3, c3, area);
    }

    /**
     * Draws the top triangle as part of the bigger triangle.
     */
    private static void drawTopTriangle(
            final PixelWriter pw,
            final int x1, final int y1,
            final int x2, final int y2,
            final int x3, final int y3,
            final Vector2f v1, final Color c1,
            final Vector2f v2, final Color c2,
            final Vector2f v3, final Color c3,
            final float area
    ) {
        final int x2x1 = x2 - x1;
        final int x3x1 = x3 - x1;
        final int y2y1 = y2 - y1;
        final int y3y1 = y3 - y1;

        for (int y = y1; y < y2; y++) {
            // No need to check if the divisors are null, because the loop will not execute if y1 == y2.
            int l = x2x1 * (y - y1) / y2y1 + x1; // Edge 1-2.
            int r = x3x1 * (y - y1) / y3y1 + x1; // Edge 1-3.
            if (l > r) { // Swap.
                int tmp = l;
                l = r;
                r = tmp;
            }
            for (int x = l; x <= r; x++) {
                final int colorBits = interpolateColor(x, y, v1, c1, v2, c2, v3, c3, area);
                pw.setArgb(x, y, colorBits);
            }
        }
    }

    /**
     * Draws the bottom triangle as part of the bigger triangle.
     */
    private static void drawBottomTriangle(
            final PixelWriter pw,
            final int x1, final int y1,
            final int x2, final int y2,
            final int x3, final int y3,
            final Vector2f v1, final Color c1,
            final Vector2f v2, final Color c2,
            final Vector2f v3, final Color c3,
            final float area
    ) {
        final int x3x2 = x3 - x2;
        final int x3x1 = x3 - x1;
        final int y3y2 = y3 - y2;
        final int y3y1 = y3 - y1;

        // Draw the separating line and bottom triangle.
        if (y3y2 == 0 || y3y1 == 0) return; // Stop now if the bottom triangle is degenerate (avoids div by zero).
        for (int y = y2; y <= y3; y++) {
            int l = x3x2 * (y - y2) / y3y2 + x2; // Edge 2-3.
            int r = x3x1 * (y - y1) / y3y1 + x1; // Edge 1-3.
            if (l > r) {
                int tmp = l;
                l = r;
                r = tmp;
            }
            for (int x = l; x <= r; x++) {
                final int colorBits = interpolateColor(x, y, v1, c1, v2, c2, v3, c3, area);
                pw.setArgb(x, y, colorBits);
            }
        }
    }

    /**
     * Interpolates the color for the given coordinate.
     * @return The interpolated color bits in the ARGB format.
     */
    private static int interpolateColor(
            final int x, final int y,
            final Vector2f v1, final Color c1,
            final Vector2f v2, final Color c2,
            final Vector2f v3, final Color c3,
            final float area
    ) {
        p.set(x, y);
        final float w1 = Math.abs(v2.to(p).crossMagnitude(v2.to(v3))) / area;
        final float w2 = Math.abs(v1.to(p).crossMagnitude(v1.to(v3))) / area;
        final float w3 = Math.abs(v1.to(p).crossMagnitude(v1.to(v2))) / area;

        final float red = clamp((float) (w1 * c1.getRed() + w2 * c2.getRed() + w3 * c3.getRed()));
        final float green = clamp((float) (w1 * c1.getGreen() + w2 * c2.getGreen() + w3 * c3.getGreen()));
        final float blue = clamp((float) (w1 * c1.getBlue() + w2 * c2.getBlue() + w3 * c3.getBlue()));

        color.set(red, green, blue);
        return color.toArgb();
    }

    /**
     * Clamps the given float value between 0 and 1.
     * @param v The value to clamp.
     * @return The clamped value.
     */
    private static float clamp(float v) {
        if (v < (float) 0.0) return (float) 0.0;
        if (v > (float) 1.0) return (float) 1.0;
        return v;
    }
}
