package com.example.lab03;

/**
 * ShapeDrawer is a functional interface for drawing shapes at specified coordinates.
 */
@FunctionalInterface
public interface ShapeDrawer {
    /**
     * Draws a shape at the specified coordinates.
     * @param x The x-coordinate where the shape will be drawn.
     * @param y The y-coordinate where the shape will be drawn.
     */
    void draw(double x, double y);
}
