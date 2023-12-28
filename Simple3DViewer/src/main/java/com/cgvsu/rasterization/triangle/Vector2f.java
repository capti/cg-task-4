package com.cgvsu.rasterization.triangle;

import java.util.Objects;

public class Vector2f {
    public float x = 0.0f;
    public float y = 0.0f;

    public Vector2f() { }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(Vector2f other) {
        this.x = other.x;
        this.y = other.y;
    }

    public Vector2f to(Vector2f end) {
        return new Vector2f(end.x - x, end.y - y);
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f add(Vector2f other) {
        Vector2f result = new Vector2f();
        result.x = this.x + other.x;
        result.y = this.y + other.y;
        return result;
    }

    public Vector2f add(float dx, float dy) {
        Vector2f result = new Vector2f();
        result.x = this.x + dx;
        result.y = this.y + dy;
        return result;
    }

    public Vector2f add(float delta) {
        Vector2f result = new Vector2f();
        result.x = this.x + delta;
        result.y = this.y + delta;
        return result;
    }

    public Vector2f sub(Vector2f other) {
        Vector2f result = new Vector2f();
        result.x = this.x - other.x;
        result.y = this.y - other.y;
        return result;
    }

    public Vector2f sub(float dx, float dy) {
        Vector2f result = new Vector2f();
        result.x = this.x - dx;
        result.y = this.y - dy;
        return result;
    }

    public Vector2f sub(float delta) {
        Vector2f result = new Vector2f();
        result.x = this.x - delta;
        result.y = this.y - delta;
        return result;
    }

    public Vector2f mul(Vector2f other) {
        Vector2f result = new Vector2f();
        result.x = this.x * other.x;
        result.y = this.y * other.y;
        return result;
    }

    public Vector2f mul(float a) {
        Vector2f result = new Vector2f();
        result.x = this.x * a;
        result.y = this.y * a;
        return result;
    }

    public Vector2f div(Vector2f other) {
        Vector2f result = new Vector2f();
        result.x = this.x / other.x;
        result.y = this.y / other.y;
        return result;
    }

    public Vector2f div(float a) {
        Vector2f result = new Vector2f();
        result.x = this.x / a;
        result.y = this.y / a;
        return result;
    }

    public float dot(Vector2f other) {
        return this.x * other.x + this.y * other.y;
    }

    public float crossMagnitude(Vector2f other) {
        return this.x * other.y - this.y * other.x;
    }

    public float getLengthSquare() {
        return x * x + y * y;
    }

    public float getLength() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public Vector2f copy() {
        return new Vector2f(this);
    }

    public Vector2f normalize() {
        Vector2f result = new Vector2f(this);
        return result.div(result.getLength());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2f vector2f = (Vector2f) o;
        return Float.compare(x, vector2f.x) == 0 && Float.compare(y, vector2f.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return String.format("v2f(x=%.3f, y=%.3f)", x, y);
    }
}
